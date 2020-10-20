package com.senac.assister.backend.domain.service;


import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.repository.ServiceRepository;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class ServicesService implements CrudService<Service> {

    private final CustomerService customerService;
    private final ServiceRepository repository;

    public ServicesService(CustomerService customerService, ServiceRepository repository) {
        this.customerService = customerService;
        this.repository = repository;
    }

    @Override
    public Service save(Service source) {
        return null;
    }

    @Override
    public Service delete(UUID id) {
        return null;
    }

    @Override
    public Service update(Service source) {
        return null;
    }

    @Override
    public Service findById(UUID id) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }
}