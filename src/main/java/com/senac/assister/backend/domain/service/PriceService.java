package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.CustomerSpecialNeeds;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.entity.SpecialNeedType;
import com.senac.assister.backend.domain.enumeration.SpecialNeedTypes;

import java.util.List;

@org.springframework.stereotype.Service
public class PriceService {

    private static final double MINIMUM_VALUE = 350;

    private static final double MINIMUM_TAX = 100;

    public static double calculateServicePrice(Service service) {
        List<CustomerSpecialNeeds> customerSpecialNeedsList = service.getClientCustomer().getCustomerSpecialNeeds();

        double totalPrice = MINIMUM_VALUE;

        for (CustomerSpecialNeeds customerSpecialNeeds :
                customerSpecialNeedsList) {
            totalPrice += getDisabilityPrice(customerSpecialNeeds.getSpecialNeedType());
        }

        return totalPrice;
    }

    private static double getDisabilityPrice(SpecialNeedType specialNeedType) {
        return MINIMUM_TAX * specialNeedType.getPrice();
    }
}