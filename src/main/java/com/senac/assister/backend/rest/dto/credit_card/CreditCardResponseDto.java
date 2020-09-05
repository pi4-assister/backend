package com.senac.assister.backend.rest.dto.credit_card;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

public class CreditCardResponseDto {
    private UUID id;

    private Customer customer;

    private String token;

    private String lastFourDigits;

    private String creditCardName;

    private String expirationDate;

    private CreditCardBrand brand;

    private boolean active;

    private Instant createdAt;

    private Instant updatedAt;

    public CreditCardResponseDto() {
    }

    public CreditCardResponseDto(UUID id, Customer customer, String token, String lastFourDigits, String creditCardName, String expirationDate, CreditCardBrand brand, boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customer = customer;
        this.token = token;
        this.lastFourDigits = lastFourDigits;
        this.creditCardName = creditCardName;
        this.expirationDate = expirationDate;
        this.brand = brand;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
