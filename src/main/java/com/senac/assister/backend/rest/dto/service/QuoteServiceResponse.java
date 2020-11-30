package com.senac.assister.backend.rest.dto.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuoteServiceResponse {

    private static final ModelMapper mapper = new ModelMapper();

    private UUID id;

    private double totalPrice;

    private ServiceStatus serviceStatus;

    private Instant createdAt;

    public static QuoteServiceResponse convertToDto(Service request) {
        return mapper.map(request, QuoteServiceResponse.class);
    }
}
