package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class CustomerAlreadyFoundException extends RuntimeException {
    public CustomerAlreadyFoundException(String identifier) {
        super("Customer " + identifier + " already found.");
    }
}