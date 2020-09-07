package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements CrudService<Customer> {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        // bussiness rule validation
        return repository.save(customer);
    }

    @Override
    public Customer delete(UUID id) {
        Customer customer = findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        // remove from all relations -> credit card, etc.
        customer.setActive(false);

        repository.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        if (findById(customer.getId()) == null) {
            throw new CustomerNotFoundException(customer.getId());
        }
        return repository.save(customer);
    }

    @Override
    public Customer findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    public List<CreditCard> getAllCreditCardsByCustomer(UUID id) {
        Customer customer = findById(id);

        return customer.getCreditCards();
    }
}