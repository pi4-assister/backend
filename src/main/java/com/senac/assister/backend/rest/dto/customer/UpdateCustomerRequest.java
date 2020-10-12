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

    @NotNull(message = "photoUrl field must be sent.")
    @Size(min = 1, max = 255, message = "photoUrl must be between 1 and 255 characters")
    private String photoUrl;

    @NotNull(message = "fullName field must be sent.")
    @Size(min = 1, max = 255, message = "fullName must be between 1 and 255 characters")
    private String fullName;

    @NotNull
    @Size(min = 1, max = 45, message = "personIdentifier must be between 1 and 45 characters")
    private String personIdentifier;

    @NotNull(message = "isLegalPerson field must be sent.")
    private boolean isLegalPerson;

    @Size(min = 1, max = 500, message = "bio must be between 1 and 500 characters")
    private String bio;

    @NotNull(message = "phoneNumber field must be sent.")
    @Size(min = 1, max = 45, message = "phoneNumber must be between 1 and 45 characters")
    private String phoneNumber;

    @NotNull(message = "customerType field must be sent.")
    private CustomerType customerType;

    private String emergencyNumber;

    @NotNull(message = "email field must be sent.")
    @Email(message = "email should be a valid e-mail.")
    @Size(min = 1, max = 255, message = "email must be between 1 and 255 characters")
    private String email;

    @NotNull(message = "password field must be sent.")
    @Size(min = 1, max = 255, message = "password must be between 1 and 255 characters")
    private String password;

    @NotNull(message = "birthdate field must be sent.")
    private Instant birthdate;

    @NotNull(message = "address field must be sent.")
    @Size(min = 1, max = 255, message = "address must be between 1 and 255 characters")
    private String address;

    @NotNull(message = "city field must be sent.")
    @Size(min = 1, max = 255, message = "city must be between 1 and 255 characters")
    private String city;

    @NotNull(message = "state field must be sent.")
    @Size(min = 1, max = 255, message = "state must be between 1 and 255 characters")
    private String state;

    @NotNull(message = "zipCode field must be sent.")
    @Size(min = 1, max = 45, message = "zipCode must be between 1 and 255 characters")
    private String zipCode;

    public static Customer convertToEntity(UpdateCustomerRequest request) {
        return mapper.map(request, Customer.class);
    }
}
