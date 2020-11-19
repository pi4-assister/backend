package com.senac.assister.backend.rest.dto.service;

import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceResponseAlterStatus {

    private static final ModelMapper mapper = new ModelMapper();

    private UUID id;

    private ServiceStatus serviceStatus;

    public static ServiceResponseAlterStatus convertToResponse(Service response) {
        return mapper.map(response, ServiceResponseAlterStatus.class);
    }
}
