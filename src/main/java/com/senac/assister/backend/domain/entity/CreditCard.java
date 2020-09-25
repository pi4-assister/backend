package com.senac.assister.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.senac.assister.backend.domain.enumeration.CreditCardBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "credit_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}