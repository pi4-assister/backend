package com.senac.assister.backend.domain.service;


import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.exception.ServiceNotFoundException;
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
        return repository.save(source);
    }

    @Override
    public Service delete(UUID id) {
        Service service = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
        service.setServiceStatus(ServiceStatus.CANCELED);
        return repository.save(service);
    }

    @Override
    public Service update(Service source) {
        return repository.save(source);
    }

    @Override
    public Service findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
    }

    @Override
    public List<Service> findAll() {
        return repository.findAll();
    }
}