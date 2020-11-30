package com.senac.assister.backend.domain.exception;

import java.util.List;
import java.util.UUID;

public class InvalidQuoteServiceException extends RuntimeException {

    public InvalidQuoteServiceException(List<String> errors) {
        super(errors.toString());
    }
}