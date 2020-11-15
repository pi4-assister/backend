package com.senac.assister.backend.rest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerSQtd {

    private UUID id;

    private String fullName;

    private Long qtdServices;
}
