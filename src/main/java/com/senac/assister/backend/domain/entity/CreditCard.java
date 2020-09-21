package com.senac.assister.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import com.senac.assister.payment.service.PaymentServiceImpl;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "token")
    private String token;

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @JsonInclude()
    @Transient
    private String creditCardNumber;

    @OneToMany(mappedBy = "creditCard")
    private List<Charge> charges = new ArrayList<>();

    @Column(name = "last_four_digits")
    private String lastFourDigits;

    @Column(name = "credit_card_name")
    private String creditCardName;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private CreditCardBrand brand;

    @Column(name = "active")
    private boolean active = true;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public CreditCard() {
    }

    public CreditCard(UUID id, Customer customer, String token, String creditCardNumber, List<Charge> charges, String lastFourDigits, String creditCardName, String expirationDate, CreditCardBrand brand, boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customer = customer;
        this.token = token;
        this.creditCardNumber = creditCardNumber;
        this.charges = charges;
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

    public String getCreditCardNumber() {
        return creditCardNumber;
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

    public List<Charge> getCharges() {
        return charges;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(lastFourDigits, that.lastFourDigits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, lastFourDigits);
    }

    public void buildCreditCard() {
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();

        this.token = paymentService.createCreditCard(this);
        this.lastFourDigits = this.creditCardNumber.substring(this.creditCardNumber.length() - 4);
        this.creditCardNumber = null;
    }
}