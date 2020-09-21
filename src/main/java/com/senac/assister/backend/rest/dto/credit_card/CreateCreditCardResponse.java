package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import org.modelmapper.ModelMapper;

public class CreateCreditCardResponse {
    private static ModelMapper mapper = new ModelMapper();

    private String token;

    public CreateCreditCardResponse() {
    }

    public CreateCreditCardResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static CreateCreditCardResponse convertToDto(CreditCard request) {
        return mapper.map(request, CreateCreditCardResponse.class);
    }
}
