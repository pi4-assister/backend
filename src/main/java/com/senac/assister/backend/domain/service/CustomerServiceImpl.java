package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer delete(UUID id) {
        Customer customer = findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        repository.delete(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
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
}
