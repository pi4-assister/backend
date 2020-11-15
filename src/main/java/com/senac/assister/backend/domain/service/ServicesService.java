package com.senac.assister.backend.domain.service;


import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.EmailSubjects;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.exception.ServiceNotFoundException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import com.senac.assister.backend.domain.repository.ServiceRepository;
import com.senac.assister.backend.rest.dto.customer.CreateCustomerRequest;
import com.senac.assister.backend.rest.dto.customer.CustomerResponse;
import com.senac.assister.backend.rest.dto.customer.CustomerSQtd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService implements CrudService<Service> {

    private final ServiceRepository repository;
    private final EmailService emailService;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper = new ModelMapper();

    public ServicesService(ServiceRepository repository, EmailService emailService, CustomerRepository customerRepository) {
        this.repository = repository;
        this.emailService = emailService;
        this.customerRepository = customerRepository;
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

    public List<CustomerSQtd> findAllAssisterInRange(Instant dateI, Instant dateF) {

        Map<UUID, Long> mapQtdServices = customerRepository.listAlQtdServices()
                .stream()
                .collect(Collectors.toMap(
                        obj -> UUID.fromString((String) obj[0]),
                        obj -> {
                            BigInteger bg = (BigInteger) obj[1];
                            return bg.longValue();
                        }
                        ));


        List<CustomerSQtd> listAssisters =
                customerRepository.findAssistersInRange(dateI, dateF).stream()
                        .map(this::convertToEntity)
                        .collect(Collectors.toList());

        listAssisters.stream().forEach(s -> s.setQtdServices(mapQtdServices.get(s.getId())));

        return listAssisters;
    }

    public Service alterStatusService(UUID id, ServiceStatus status) {
        Service service = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
        service.setServiceStatus(status);
        return repository.save(service);
    }

    public void sendEmail(UUID id) {
        Service service = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

        Customer customer = service.getClientCustomer();

        Customer assister = service.getAssisterCustomer();

        emailService.sendHtmlEmail(customer, EmailSubjects.SERVICE_IN_PROGRESS);

        emailService.sendHtmlEmail(assister, EmailSubjects.SERVICE_IN_PROGRESS);
    }

    public CustomerSQtd convertToEntity(Customer customer) {
        return mapper.map(customer, CustomerSQtd.class);
    }

}
