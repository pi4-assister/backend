package com.senac.assister.backend.rest.dto.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Charge;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Rate;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceResponse {

    private static ModelMapper mapper = new ModelMapper();

    private String id;

    private Customer assisterCustomer;

    private Customer clientCustomer;

    private Instant startDate;

    private Instant finalDate;

    private String description;

    private double totalPrice;

    @JsonIgnore
    private Charge charge;

    private Rate rate;

    private ServiceStatus serviceStatus;

    public static ServiceResponse convertToResponse(Service request) {
        return mapper.map(request, ServiceResponse.class);
    }

}
