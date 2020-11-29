package com.senac.assister.backend.domain.service;


import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.EmailSubjects;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.domain.exception.InvalidQuoteServiceException;
import com.senac.assister.backend.domain.exception.ServiceNotFoundException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import com.senac.assister.backend.domain.repository.RateRepository;
import com.senac.assister.backend.domain.repository.ServiceRepository;
import com.senac.assister.backend.domain.security.MyUserDetails;
import com.senac.assister.backend.domain.validation.FinishServiceValidation;
import com.senac.assister.backend.domain.validation.QuoteServiceValidation;
import com.senac.assister.backend.rest.dto.customer.CustomerSQtd;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService implements CrudService<Service> {

    private final ServiceRepository repository;
    private final EmailService emailService;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ChargeService chargeService;
    private final RateRepository rateRepository;

    private final ModelMapper mapper = new ModelMapper();

    public ServicesService(ServiceRepository repository, EmailService emailService, CustomerRepository customerRepository, CustomerService customerService, ChargeService chargeService, RateRepository rateRepository) {
        this.repository = repository;
        this.emailService = emailService;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.chargeService = chargeService;
        this.rateRepository = rateRepository;
    }

    public Service quoteService(Service service) {
        Customer client = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).loggedCustomer;

        service.setClientCustomer(client);

        service.setAssisterCustomer(customerService.findById(service.getAssisterCustomer().getId()));

        List<String> errors = QuoteServiceValidation.isValid(service);

        if (!errors.isEmpty()) {
            throw new InvalidQuoteServiceException(errors);
        }

        service.setTotalPrice(PriceService.calculateServicePrice(service));

        service.setServiceStatus(ServiceStatus.QUOTED);

        emailService.sendServiceHtmlEmail(service, EmailSubjects.QUOTE);

        return repository.save(service);
    }

    public Service finishService(Service request) {
        Service service = findById(request.getId());

        List<String> errors = FinishServiceValidation.isValid(service);

        if (!errors.isEmpty()) {
            throw new InvalidQuoteServiceException(errors);
        }

        service.setServiceStatus(ServiceStatus.FINISHED);

        return repository.save(service);
    }

    public Service payService(Service service) {
        Charge charge = chargeService.chargeService(service);

        service.setCharge(charge);

        service.setServiceStatus(ServiceStatus.PAID);

        return repository.save(service);
    }

    public Service cancelService(Service service) {
        if (service.getServiceStatus() != ServiceStatus.IN_PROGRESS) {

            Duration range = Duration.between(service.getUpdatedAt(), Instant.now().minusSeconds(86400 * 10)); // 10 days
            long days = range.toDays();

            if (days > 5) {
                service.setServiceStatus(ServiceStatus.CANCELED);

                return repository.save(service);
            }
        }
        return repository.save(service);
    }

    public List<CustomerSQtd> findAllAssisterInRange(Instant dateI, Instant dateF) {

        Map<UUID, CustomerSQtd> mapAssisters = new HashMap<UUID, CustomerSQtd>();

        Map<UUID, Long> mapAmountServices = customerRepository.findAllAmountServices()
                .stream()
                .collect(Collectors.toMap(
                        obj -> UUID.fromString((String) obj[0]),
                        obj -> {
                            BigInteger bg = (BigInteger) obj[1];
                            return bg.longValue();
                        }
                ));

        Map<UUID, Integer> mapRateNotes = rateRepository.findAllRateNotes()
                .stream()
                .collect(Collectors.toMap(
                        obj -> UUID.fromString((String) obj[0]),
                        obj -> ((BigInteger) obj[1]).intValue()
                ));

        List<CustomerSQtd> listAssisters =
                customerRepository.findAssistersInRange(dateI, dateF).stream()
                        .map(this::convertToEntity)
                        .collect(Collectors.toList());

        listAssisters.stream().forEach(s -> s.setQtdServices(mapAmountServices.get(s.getId())));

        listAssisters.stream().forEach(s -> {
            if (mapRateNotes.get(s.getId()) != null) s.setRate(mapRateNotes.get(s.getId()));
        });

        listAssisters.forEach(s -> {
            mapAssisters.put(s.getId(), s);
        });

        customerRepository.findAll().forEach(c -> {
            CustomerSQtd assister = mapAssisters.get(c.getId());
            if (assister != null) assister.setSpecialNeeds(c.getCustomerSpecialNeeds());
        });


        listAssisters = new ArrayList(mapAssisters.values());
        listAssisters.sort(Comparator.comparingLong(CustomerSQtd::getQtdServices).reversed());

        return listAssisters;
    }

    public Service alterStatusService(UUID id, ServiceStatus status) {
        Service service = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));
        service.setServiceStatus(status);
        return repository.save(service);
    }

    public List<Service> getAllServicesByStatus(ServiceStatus status) {
        return repository.findAllByServiceStatus(status);
    }

    public List<Charge> getAllChargesByCustomer(Customer customer) {
        List<Charge> listOfCharges = new ArrayList<>();

        List<Service> listOfServices = getAllCustomerServicesByStatus(customer, ServiceStatus.PAID);

        listOfServices.forEach(service -> {
            listOfCharges.add(service.getCharge());
        });

        return listOfCharges;
    }

    public List<Service> getAllPaidServicesByCustomer(Customer customer) {
        return getAllCustomerServicesByStatus(customer, ServiceStatus.PAID);
    }

    public List<Service> getAllCustomerServicesByStatus(Customer customer, ServiceStatus status) {
        if (status == null) {
            return repository.findAllByClientCustomerIdOrAssisterCustomerId(customer.getId(), customer.getId());
        }
        return repository.findAllByClientCustomerIdOrAssisterCustomerIdAndServiceStatus(customer.getId(), customer.getId(), status);
    }

    public List<Service> getAllPendingServices() {

        List<Service> listOfAllServices = new ArrayList<>();

        repository.findAll().forEach(service -> {
            if (service.getServiceStatus() != ServiceStatus.CANCELED ||
                    service.getServiceStatus() != ServiceStatus.FINISHED ||
                    service.getServiceStatus() != ServiceStatus.PAID ||
                    service.getServiceStatus() != ServiceStatus.IN_PROGRESS) {
                listOfAllServices.add(service);
            }
        });

        return listOfAllServices;
    }

    public void sendEmail(UUID id) {
        Service service = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

        emailService.sendServiceHtmlEmail(service, EmailSubjects.SERVICE_IN_PROGRESS);
    }

    public CustomerSQtd convertToEntity(Customer customer) {
        return mapper.map(customer, CustomerSQtd.class);
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
