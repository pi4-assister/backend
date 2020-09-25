package com.senac.assister.backend.domain.service;


import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.exception.CreditCardNotFoundException;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CreditCardRepository;
import com.senac.assister.payment.service.PaymentServiceImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreditCardService implements CrudService<CreditCard> {

    private final CreditCardRepository repository;
    private final CustomerService customerService;
    private final PaymentServiceImpl paymentService;

    public CreditCardService(CreditCardRepository repository, CustomerService customerService, PaymentServiceImpl paymentService) {
        this.repository = repository;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    @Override
    public CreditCard save(CreditCard source) {
        customerService.findById(source.getCustomer().getId())
                .orElseThrow(() -> new CustomerNotFoundException(source.getCustomer().getId()));

        source.setCreatedAt(Instant.now());
        source.setUpdatedAt(Instant.now());

        source.setToken(paymentService.tokenization(source));
        source.setLastFourDigits(source.getCreditCardNumber()
                .substring(source.getCreditCardNumber().length() - 4));

        source.setCreditCardNumber("");

        return repository.save(source);
    }

    @Override
    public CreditCard delete(UUID id) {
        CreditCard card = repository.findByIdAndActiveTrue(id).orElseThrow(() -> new CreditCardNotFoundException(id));

        disableCreditCard(card);

        return card;
    }

    @Override
    public CreditCard update(CreditCard source) {
        CreditCard card = repository.findByIdAndActiveTrue(source.getId()).orElseThrow(
                () -> new CreditCardNotFoundException(source.getId()));

        return repository.save(card);
    }

    @Override
    public Optional<CreditCard> findById(UUID id) {
        return repository.findByIdAndActiveTrue(id);
    }

    @Override
    public List<CreditCard> findAll() {
        return repository.findAll();
    }

    public List<CreditCard> findAllByCustomerId(UUID id) {
        return repository.findAllByCustomerIdAndActiveTrue(id);
    }

    public void disableAllCustomerCreditCards(UUID customerId) {
        repository.findAllByCustomerIdAndActiveTrue(customerId)
                .forEach(this::disableCreditCard);
    }

    private void disableCreditCard(CreditCard card) {
        card.setActive(false);
        repository.save(card);
    }
}