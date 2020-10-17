package com.senac.assister.backend.rest.dto.customer;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {

    private static final ModelMapper mapper = new ModelMapper();

    private String id;

    private String photoUrl;

    private String fullName;

    private String personIdentifier;

    private String bio;

    private String phoneNumber;

    private String emergencyNumber;

    private CustomerType customerType;

    private CustomerStatus customerStatus;

    private String email;

    private Instant birthdate;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    public static CustomerResponse convertToDto(Customer customer) {
        return mapper.map(customer, CustomerResponse.class);
    }
}
