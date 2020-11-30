package com.senac.assister.backend.domain.exception;

import java.util.UUID;

public class SpecialNeedTypeNotFoundException extends RuntimeException {

    public SpecialNeedTypeNotFoundException(UUID uuid) {
        super("Special need type " + uuid.toString() + " not found.");
    }

    public SpecialNeedTypeNotFoundException(String name) {
        super("Special need type " + name + " not found.");
    }
}