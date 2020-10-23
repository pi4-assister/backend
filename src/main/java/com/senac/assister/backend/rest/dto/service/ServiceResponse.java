package com.senac.assister.backend.rest.dto.service;

import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import com.senac.assister.backend.rest.dto.rate.CreateRateRequest;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

public class ServiceResponse {

    private static ModelMapper mapper = new ModelMapper();

    private String id;

    private Customer assisterCustomer;

    private Customer clientCustomer;

    private Instant startDate;

    private Instant finalDate;

    private String description;

    private double totalPrice;

    private Charge charge;

    private Rate rate;

    private ServiceStatus serviceStatus;

    public static ServiceResponse convertToResponse(Service request) {
        return mapper.map(request, ServiceResponse.class);
    }

}
