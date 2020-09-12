package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.CreditCard;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class CreditCardResponse {
    private static ModelMapper mapper = new ModelMapper();

    private UUID id;

    private String token;

    private String lastFourDigits;

    private String creditCardName;

    private String expirationDate;

    private CreditCardBrand brand;

    public CreditCardResponse() {
    }

    public CreditCardResponse(UUID id, String token, String lastFourDigits, String creditCardName, String expirationDate, CreditCardBrand brand) {
        this.id = id;
        this.token = token;
        this.lastFourDigits = lastFourDigits;
        this.creditCardName = creditCardName;
        this.expirationDate = expirationDate;
        this.brand = brand;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
