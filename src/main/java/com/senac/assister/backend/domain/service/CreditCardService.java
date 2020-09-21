package com.senac.assister.backend.domain.service;


import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditCardService implements CrudService<CreditCard> {

    private final CreditCardRepository repository;

    private final CustomerService customerService;

    public CreditCardService(CreditCardRepository repository, CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @Override
    public CreditCard save(CreditCard source) {
        if (!customerService.findById(source.getId()).isPresent()) {
            throw new CustomerNotFoundException(source.getId());
        }

        disableAllCustomerCreditCards(source.getCustomer().getId());

        source.buildCreditCard();

        return repository.save(source);
    }

    @Override
    public CreditCard delete(UUID id) {
        return null;
    }

    @Override
    public CreditCard update(CreditCard source) {
        return null;
    }

    @Override
    public Optional<CreditCard> findById(UUID id) {
        return null;
    }

    @Override
    public List<CreditCard> findAll() {
        return null;
    }

    public void disableAllCustomerCreditCards(UUID id) {
        List<CreditCard> listOfCreditCards = repository.findAllByCustomerIdAndActiveTrue(id); // and active true

        for (CreditCard card :
                listOfCreditCards) {
            disableCreditCard(card);
        }
    }

    private void disableCreditCard(CreditCard card) {
        card.setActive(false);
        repository.save(card);
    }
}