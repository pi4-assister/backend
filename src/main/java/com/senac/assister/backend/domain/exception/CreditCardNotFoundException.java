package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class CreditCardNotFoundException extends RuntimeException {

    public CreditCardNotFoundException(UUID uuid) {
        super("Credit card " + uuid.toString() + " not found.");
    }
}