package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(UUID uuid){
        super("Service " + uuid.toString() + " not found.");
    }
}
