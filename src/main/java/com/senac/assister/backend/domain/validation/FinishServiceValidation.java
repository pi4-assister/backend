package com.senac.assister.backend.domain.validation;

import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;

import java.util.ArrayList;
import java.util.List;

public class FinishServiceValidation {
    public static List<String> isValid(Service service) {
        List<String> errors = new ArrayList<>();

        if (service.getServiceStatus() != ServiceStatus.IN_PROGRESS) {
            errors.add("Service must be in progress.");
        }

        return errors;
    }
}

