package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import com.senac.assister.backend.domain.repository.CreditCardRepository;
import com.senac.assister.payment.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
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


import static org.junit.jupiter.api.Assertions.*;

class CreditCardServiceTest {
    CreditCardRepository creditCardRepository = mock(CreditCardRepository.class);
    CustomerService customerService = mock(CustomerService.class);
    PaymentServiceImpl paymentService = mock(PaymentServiceImpl.class);

    private final CreditCardService creditCardService = new CreditCardService(creditCardRepository,
            customerService,
            paymentService);

    @Test
    void Create_CreditCard_Success() {
        CreditCard card = createCreditCard();

        when(customerService.findById(card.getCustomer().getId())).thenReturn(card.getCustomer());
        when(paymentService.tokenization(card)).thenReturn(UUID.randomUUID().toString());

        creditCardService.save(card);

        assert card.getHolderNumber().length() == 4;
        assert card.getToken() != null;

        verify(creditCardRepository, times(1)).save(card);
    }

    @Test
    void Create_CreditCardCustomerNotFound_Fail() {
        CreditCard card = createCreditCard();

        when(customerService.findById(card.getCustomer().getId())).thenThrow(new CustomerNotFoundException());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            creditCardService.save(card);
        });

        Assertions.assertEquals("Customer not found.", exception.getMessage());

        verify(creditCardRepository, never()).save(card);
    }

    @Test
    void delete() {
        CreditCard card = createCreditCard();

        when(creditCardRepository.findByIdAndActiveTrue(card.getId())).thenReturn(Optional.of(card));

        creditCardService.delete(card.getId());

        verify(creditCardRepository, times(1)).save(card);
    }

    private CreditCard createCreditCard() {
        CreditCard card = new CreditCard();
        card.setId(UUID.randomUUID());
        card.setCustomer(createCustomer());
        card.setHolderNumber("123456789123456789");
        card.setHolderName("Teste name");
        card.setExpirationDate("04/27");
        card.setBrand(CreditCardBrand.MASTERCARD);

        return card;
    }

    private Customer createCustomer() {
        return new Customer(
                UUID.randomUUID(),
                "photoUrl",
                "Full name",
                "08976537539",
                "bio",
                "957823402",
                "923402148",
                CustomerType.CLIENT,
                CustomerStatus.REGISTERED,
                "email@gmail.com",
                "fd4c2b1441c3319eaa14e1d63f64336e",
                "123456",
                Instant.now(),
                "address",
                "city",
                "state",
                "04234098",
                Instant.now(),
                Instant.now()
        );
    }
}
