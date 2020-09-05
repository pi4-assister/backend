package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer save(Customer customer);

    Customer delete(UUID id);

    Customer update(Customer customer);

    Customer findById(UUID id);

    List<Customer> findAll();
}