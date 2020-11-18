package com.senac.assister.backend.rest.dto.service;

import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class ServiceResponseAlterStatus {

    private static final ModelMapper mapper = new ModelMapper();

    private UUID id;

    private ServiceStatus serviceStatus;

    public static ServiceResponseAlterStatus convertToResponse(Service response) {
        return mapper.map(response, ServiceResponseAlterStatus.class);
    }
}
