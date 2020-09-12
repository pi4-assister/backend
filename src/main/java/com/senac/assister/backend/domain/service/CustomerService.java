package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.exception.CustomerAlreadyFoundException;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CreditCardRepository;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import com.senac.assister.backend.domain.security.Hash;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService implements CrudService<Customer> {

    private final CustomerRepository repository;
    private final CreditCardRepository creditCardRepository;

    public CustomerService(CustomerRepository repository, CreditCardRepository creditCardRepository) {
        this.repository = repository;
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerFound = repository.findByEmailAndActiveTrue(customer.getEmail());

        if (customerFound.isPresent()) {
            throw new CustomerAlreadyFoundException(customerFound.get().getEmail());
        }

        customer.setPassword(encryptPassword(customer.getPassword()));
        customer.setStatus(CustomerStatus.HIRED);
        return repository.save(customer);
    }

    @Override
    public Customer delete(UUID id) {
        Optional<Customer> req = findById(id);

        Customer customer = req.orElseThrow(() -> new CustomerNotFoundException(id));

        disableCustomerCreditCards(id);

        customer.setActive(false);

        return repository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer customerFound = findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException(customer.getId()));

        if (!customerFound.getPassword().equals(customer.getPassword())) {
            customer.setPassword(encryptPassword(customer.getPassword()));
        }

        return repository.save(customer);
    }

    public Customer show(UUID id) {
        return findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    public List<CreditCard> getAllCreditCardsByCustomer(UUID id) {
        Customer customer = findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        return customer.getCreditCards();
    }

    private String encryptPassword(String password) {
        return Hash.convertToMd5(password);
    }

    private List<CreditCard> disableCustomerCreditCards(UUID id) {
        return getAllCreditCardsByCustomer(id)
                .stream()
                .map(creditCard -> {
                    creditCard.setActive(false);
                    return creditCardRepository.save(creditCard);
                })
                .collect(Collectors.toList());
    }
}