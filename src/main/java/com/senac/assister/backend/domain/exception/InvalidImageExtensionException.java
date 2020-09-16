package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class InvalidImageExtensionException extends RuntimeException {

    public InvalidImageExtensionException(String type) {
        super(type + " isn't a valid image type.");
    }
}