package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CreditCardRepository;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final ImageServiceImpl imageService = mock(ImageServiceImpl.class);
    private final EmailService emailService = mock(EmailService.class);
    private final SpecialNeedTypeService specialNeedTypeService = mock(SpecialNeedTypeService.class);
    private final CustomerService customerService = new CustomerService(customerRepository, imageService, emailService, specialNeedTypeService);

    @Test
    void Create_SuccessCustomer_Success() {
        Customer customer = createCustomer();
        customerService.save(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void Update_Customer_Success() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndStatusNot(customer.getId(), CustomerStatus.CANCELED)).thenReturn(Optional.of(customer));
        customerService.update(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void Update_CustomerNotFound_Fail() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndStatusNot(customer.getId(), CustomerStatus.CANCELED)).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.update(customer);
        });

        Assertions.assertEquals("Customer " + customer.getId().toString() + " not found.", exception.getMessage());

        verify(customerRepository, never()).save(customer);
    }

    @Test
    void Delete_Customer_Success() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndStatusNot(customer.getId(), CustomerStatus.CANCELED)).thenReturn(Optional.of(customer));
        customerService.delete(customer.getId());

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void Delete_CustomerNotFound_Fail() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndStatusNot(customer.getId(), CustomerStatus.CANCELED)).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.delete(customer.getId());
        });

        Assertions.assertEquals("Customer " + customer.getId().toString() + " not found.", exception.getMessage());

        verify(customerRepository, never()).save(customer);
    }

    private Customer createCustomer() {
        return new Customer(
                UUID.randomUUID(),
                "photoUrl",
                "fullName",
                "personIdentifier",
                "biooo",
                "902020202",
                "902020202",
                CustomerType.CLIENT,
                CustomerStatus.REGISTERED,
                "email",
                new ArrayList<>(),
                "password",
                "forgetPasswordCode",
                Instant.now(),
                "address",
                "city",
                "state",
                "zipCode",
                Instant.now(),
                Instant.now()
        );
    }
}