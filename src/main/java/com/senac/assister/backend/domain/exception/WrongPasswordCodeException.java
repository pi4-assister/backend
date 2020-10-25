package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class WrongPasswordCodeException extends RuntimeException {
    public WrongPasswordCodeException() {
        super("Wrong password code.");
    }
}