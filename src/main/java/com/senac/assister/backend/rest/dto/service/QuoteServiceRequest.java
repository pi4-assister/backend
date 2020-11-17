package com.senac.assister.backend.rest.dto.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Service;
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
public class QuoteServiceRequest {

    private static final ModelMapper mapper = new ModelMapper();

    @JsonIgnore
    private UUID id;

    @NotNull(message = "assisterCustomerId field must be sent.")
    private UUID assisterCustomerId;

    @NotNull(message = "startDate field must be sent.")
    private Instant startDate;

    @NotNull(message = "finalDate field must be sent.")
    private Instant finalDate;

    @NotNull(message = "description field must be sent.")
    private String description;

    public static Service convertToEntity(QuoteServiceRequest request) {
        return mapper.map(request, Service.class);
    }
}
