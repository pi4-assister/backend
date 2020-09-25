package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CreditCardRepository;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    private final CustomerService customerService = new CustomerService(customerRepository, imageService, emailService);

    @Test
    void Create_SuccessCustomer_Success() {
        Customer customer = createCustomer();
        customerService.save(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void Update_Customer_Success() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndActiveTrue(customer.getId())).thenReturn(Optional.of(customer));
        customerService.update(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void Update_CustomerNotFound_Fail() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndActiveTrue(customer.getId())).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.update(customer);
        });

        Assertions.assertEquals("Customer " + customer.getId().toString() + " not found.", exception.getMessage());

        verify(customerRepository, never()).save(customer);
    }

    @Test
    void Delete_Customer_Success() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndActiveTrue(customer.getId())).thenReturn(Optional.of(customer));
        customerService.delete(customer.getId());

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void Delete_CustomerNotFound_Fail() {
        Customer customer = createCustomer();

        when(customerRepository.findByIdAndActiveTrue(customer.getId())).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.delete(customer.getId());
        });

        Assertions.assertEquals("Customer " + customer.getId().toString() + " not found.", exception.getMessage());

        verify(customerRepository, never()).save(customer);
    }


    private Customer createCustomer() {
        return new Customer(
                UUID.randomUUID(),
                "fullName",
                "personIdentifier",
                true,
                "testebio",
                "965234567",
                CustomerType.CLIENT,
                CustomerStatus.REGISTERED,
                null,
                "teste@teste.com",
                "12345678",
                Instant.now(),
                "asdkasdkoasd 1100",
                "Sao Paulo",
                "Sao Paulo",
                "04672234",
                true,
                Instant.now(),
                Instant.now()
        );
    }
}