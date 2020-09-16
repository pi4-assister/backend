package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class CreditCardResponse {
    private static ModelMapper mapper = new ModelMapper();

    private String token;

    private String lastFourDigits;

    private String creditCardName;

    private CreditCardBrand brand;

    public CreditCardResponse() {
    }

    public CreditCardResponse(String token, String lastFourDigits, String creditCardName, CreditCardBrand brand) {
        this.token = token;
        this.lastFourDigits = lastFourDigits;
        this.creditCardName = creditCardName;
        this.brand = brand;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public CreditCardBrand getBrand() {
        return brand;
    }

    public void setBrand(CreditCardBrand brand) {
        this.brand = brand;
    }

    public static CreditCardResponse convertToDto(CreditCard request) {
        return mapper.map(request, CreditCardResponse.class);
    }
}
