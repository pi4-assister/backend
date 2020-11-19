package com.senac.assister.backend.rest.dto.customer;

import com.senac.assister.backend.domain.entity.CustomerSpecialNeeds;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
