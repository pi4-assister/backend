package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.CustomerSpecialNeeds;
import com.senac.assister.backend.domain.entity.SpecialNeedType;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.EmailSubjects;
import com.senac.assister.backend.domain.exception.CustomerAlreadyFoundException;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.exception.WrongPasswordCodeException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import com.senac.assister.backend.domain.security.AssisterPasswordEncoder;
import com.senac.assister.backend.domain.security.Hash;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CrudService<Customer> {

    private final CustomerRepository repository;
    private final ImageServiceImpl imageService;
    private final EmailService emailService;
    private final SpecialNeedTypeService specialNeedTypeService;

    private final PasswordEncoder encoder = AssisterPasswordEncoder.encoder();

    public CustomerService(CustomerRepository repository, ImageServiceImpl imageService, EmailService emailService, SpecialNeedTypeService specialNeedTypeService) {
        this.repository = repository;
        this.imageService = imageService;
        this.emailService = emailService;
        this.specialNeedTypeService = specialNeedTypeService;
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerFound = repository.findByEmailAndStatusNot(customer.getEmail(), CustomerStatus.CANCELED);

        if (customerFound.isPresent()) {
            throw new CustomerAlreadyFoundException(customerFound.get().getEmail());
        }

        customer.setPassword(encryptPassword(customer.getPassword()));

        for (CustomerSpecialNeeds customerSpecialNeeds :
                customer.getCustomerSpecialNeeds()) {
            SpecialNeedType specialNeedType = specialNeedTypeService.findByName(customerSpecialNeeds.getSpecialNeedType().getName());
            customerSpecialNeeds.setSpecialNeedType(specialNeedType);

            customerSpecialNeeds.setCustomer(customer);
        }

        customer.setStatus(CustomerStatus.REGISTERED);

        emailService.sendHtmlEmail(customer, EmailSubjects.CREATE_USER);

        return repository.save(customer);
    }

    @Override
    public Customer delete(UUID id) {
        Customer customer = findById(id);

        customer.setStatus(CustomerStatus.CANCELED);

        return repository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer customerFound = findById(customer.getId());

        return repository.save(customer);
    }

    public Customer show(UUID id) {
        return findById(id);
    }

    @Override
    public Customer findById(UUID id) {
        return repository.findByIdAndStatusNot(id, CustomerStatus.CANCELED).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    public String uploadProfilePicture(MultipartFile profilePicture, UUID id) {
        Customer customer = findById(id);

        String url = imageService.upload(profilePicture, customer);

        customer.setStatus(CustomerStatus.HIRED);
        customer.setPhotoUrl(url);
        repository.save(customer);

        return customer.getPhotoUrl();
    }

    public void changePassword(UUID id, String newPassword, String code) {
        Customer customer = findById(id);

        if (!customer.getForgetPasswordCode().equals(code)) {
            throw new WrongPasswordCodeException();
        }

        customer.setPassword(encryptPassword(newPassword));
        customer.setForgetPasswordCode("");

        repository.save(customer);
    }

    public void generatePasswordCode(String email) {
        Customer customer = repository.findByEmailAndStatusNot(email, CustomerStatus.CANCELED)
                .orElseThrow(CustomerNotFoundException::new);

        String passwordCode = Hash.convertToMd5(customer.getPassword() + Instant.now());
        passwordCode = passwordCode.substring(0, 6).toUpperCase();

        customer.setForgetPasswordCode(passwordCode);
        repository.save(customer);

        emailService.sendHtmlEmail(customer, EmailSubjects.FORGOT_PASSWORD);
    }

    private String encryptPassword(String password) {
        return encoder.encode(password);
    }
}