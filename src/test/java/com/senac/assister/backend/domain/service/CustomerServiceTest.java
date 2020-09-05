package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.customer.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.customer.CustomerType;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private CustomerService customerService = new CustomerService(customerRepository);

    @Test
    void save() {
        Customer customer = createCustomer();
        customerService.save(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void updateSuccess() {
        Customer customer = createCustomer();

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        customerService.update(customer);

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void updateWhenNotExists() {
        Customer customer = createCustomer();

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.update(customer);
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
                true,
                "testebio",
                "965234567",
                CustomerType.CLIENT,
                CustomerStatus.HIRED,
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