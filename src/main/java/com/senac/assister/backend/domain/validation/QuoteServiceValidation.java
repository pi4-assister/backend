package com.senac.assister.backend.domain.validation;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class QuoteServiceValidation {
    public static List<String> isValid(Service service) {
        List<String> errors = new ArrayList<>();

        if (!isAssister(service.getAssisterCustomer())) {
            errors.add("Assister type is invalid");
        }

        if (!isClient(service.getClientCustomer())) {
            errors.add("Client type is invalid");
        }

        if (isDateRangeValid(service.getStartDate(), service.getFinalDate())) {
            errors.add("Date range is invalid");
        }

        if (isCustomerStatusInvalid(service.getClientCustomer())) {
            errors.add("Client need to be HIRED");
        }

        if (isCustomerStatusInvalid(service.getAssisterCustomer())) {
            errors.add("Assister need to be HIRED");
        }

        return errors;
    }

    private static boolean isAssister(Customer customer) {
        return customer.getCustomerType().toString().equalsIgnoreCase("ASSISTER");
    }

    private static boolean isClient(Customer customer) {
        return customer.getCustomerType().toString().equalsIgnoreCase("CLIENT");
    }

    private static boolean isDateRangeValid(Instant startDate, Instant finalDate) {
        return finalDate.isBefore(startDate) || startDate.isAfter(finalDate);
    }

    private static boolean isCustomerStatusInvalid(Customer customer) {
        return !customer.getStatus().toString().equalsIgnoreCase("HIRED");
    }
}

