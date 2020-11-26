package com.senac.assister.backend.rest.dto.customer;

import com.senac.assister.backend.domain.entity.CustomerSpecialNeeds;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerSQtd {

    private UUID id;

    private String fullName;

    private String photoUrl;

    private Long qtdServices;

    private List<CustomerSpecialNeeds> SpecialNeeds;

    private String personIdentifier;

    private String bio;

    private String phoneNumber;

    private String emergencyNumber;

    private CustomerType customerType;

    private CustomerStatus status;

    private String email;

    private Instant birthdate;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private Instant createdAt;

    private Instant updatedAt;

    private Double note = 0.0;


}
