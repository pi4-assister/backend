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
public class CreateCustomerResponse {

    private static final ModelMapper mapper = new ModelMapper();

    private String id;

    private String photoUrl;

    private Instant createdAt;

    public static CreateCustomerResponse convertToDto(Customer customer) {
        return mapper.map(customer, CreateCustomerResponse.class);
    }
}
