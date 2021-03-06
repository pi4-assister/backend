package com.senac.assister.backend.rest.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCustomerRequest {

    private static final ModelMapper mapper = new ModelMapper();

    @JsonIgnore
    private UUID id;

    @Size(min = 1, max = 255, message = "photoUrl must be between 1 and 255 characters")
    private String photoUrl;

    @Size(min = 1, max = 255, message = "fullName must be between 1 and 255 characters")
    private String fullName;

    @Size(min = 1, max = 500, message = "bio must be between 1 and 500 characters")
    private String bio;

    @Size(min = 1, max = 45, message = "phoneNumber must be between 1 and 45 characters")
    private String phoneNumber;

    @Size(min = 1, max = 45, message = "emergencyNumber must be between 1 and 45 characters")
    private String emergencyNumber;

    private Instant birthdate;

    @Size(min = 1, max = 255, message = "address must be between 1 and 255 characters")
    private String address;

    @Size(min = 1, max = 255, message = "city must be between 1 and 255 characters")
    private String city;

    @Size(min = 1, max = 255, message = "state must be between 1 and 255 characters")
    private String state;

    @Size(min = 1, max = 45, message = "zipCode must be between 1 and 255 characters")
    private String zipCode;

    public static Customer convertToEntity(UpdateCustomerRequest request) {
        return mapper.map(request, Customer.class);
    }
}
