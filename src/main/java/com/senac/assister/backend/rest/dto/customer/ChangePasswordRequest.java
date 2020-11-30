package com.senac.assister.backend.rest.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {

    private static final ModelMapper mapper = new ModelMapper();

    @JsonIgnore
    private UUID id;

    @NotNull(message = "password field must be sent.")
    @Size(min = 1, max = 255, message = "password must be between 1 and 255 characters")
    private String password;

    @NotNull(message = "code field must be sent.")
    @Size(min = 1, max = 6, message = "code must be between 1 and 6 characters")
    private String code;

    public static Customer convertToEntity(ChangePasswordRequest request) {
        return mapper.map(request, Customer.class);
    }
}
