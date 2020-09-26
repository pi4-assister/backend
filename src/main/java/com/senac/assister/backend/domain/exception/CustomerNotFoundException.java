package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID uuid) {
        super("Customer " + uuid.toString() + " not found.");
    }

    public CustomerNotFoundException() {
        super("Customer not found.");
    }
}