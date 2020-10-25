package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class RateNotFoundException extends RuntimeException {

    public RateNotFoundException(UUID id){
        super("Rate " + id.toString() + "not found.");
    }
}
