package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.CustomerSpecialNeeds;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.entity.SpecialNeedType;
import com.senac.assister.backend.domain.enumeration.SpecialNeedTypes;

import java.time.*;
import java.time.temporal.Temporal;
import java.util.List;

import static org.threeten.bp.temporal.ChronoUnit.DAYS;

@org.springframework.stereotype.Service
public class PriceService {

    private static final double MINIMUM_VALUE = 200;
    private static final double MINIMUM_TAX = 60;
    private static final double PRICE_PER_DAY = 20;

    public static double calculateServicePrice(Service service) {
        List<CustomerSpecialNeeds> customerSpecialNeedsList = service.getClientCustomer().getCustomerSpecialNeeds();

        double totalPrice = MINIMUM_VALUE;

        for (CustomerSpecialNeeds customerSpecialNeeds :
                customerSpecialNeedsList) {
            totalPrice += getDisabilityPrice(customerSpecialNeeds.getSpecialNeedType());
        }

        totalPrice = totalPrice + (totalPrice * getAgePrice(service.getClientCustomer().getBirthdate()));

        totalPrice += getPricePerDay(service.getStartDate(), service.getFinalDate());

        return totalPrice;
    }

    private static double getDisabilityPrice(SpecialNeedType specialNeedType) {
        return MINIMUM_TAX * specialNeedType.getPrice();
    }

    private static double getPricePerDay(Instant start, Instant finish) {
        Duration range = Duration.between(start, finish);
        long days = range.toDays();

        return days * PRICE_PER_DAY;
    }

    private static double getAgePrice(Instant birthday) {
        LocalDate birthdate
                = LocalDateTime.ofInstant(birthday, ZoneOffset.UTC).toLocalDate();

        int ageNow = birthdate.getYear();

        if (ageNow < 20) {
            return 0.05;
        }

        if (ageNow < 40) {
            return 0.1;
        }

        if (ageNow < 60) {
            return 0.25;
        } else {
            return 0.3;
        }
    }
}