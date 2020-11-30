package com.senac.assister.backend.rest.dto.service;

import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class ServiceRequest {

    private static final ModelMapper mapper = new ModelMapper();

    @NotNull(message = "need assister in service")
    private Customer assisterCustomer;

    @NotNull(message = "need client in service")
    private Customer clientCustomer;

    @NotNull(message = "need start date in service")
    private Instant startDate;

    @NotNull(message = "need final date in service")
    private Instant finalDate;

    @NotNull(message = "service need description")
    private String description;

    @NotNull(message = "service need price")
    private double totalPrice;

    @NotNull(message = "service need chrage")
    private Charge charge;

    private Rate rate;

    @NotNull(message = "service need status")
    private ServiceStatus serviceStatus;

    public static Service convertToEntity(ServiceRequest request) {
        return mapper.map(request, Service.class);
    }
}
