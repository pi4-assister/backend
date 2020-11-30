package com.senac.assister.backend.rest.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetPasswordCodeResponse {

    private static final ModelMapper mapper = new ModelMapper();

    private String code;

    public static GetPasswordCodeResponse convertToEntity(Customer request) {
        return mapper.map(request, GetPasswordCodeResponse.class);
    }
}
