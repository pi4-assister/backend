package com.senac.assister.backend.domain.entity;

import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    public Customer(String photoUrl, String fullName, String personIdentifier, String bio, String phoneNumber, String emergencyNumber, CustomerType customerType, CustomerStatus status, String email, String encrypted_password, Instant birthdate, String address, String city, String state, String zipCode, Instant createdAt, Instant updatedAt) {
        this.photoUrl = photoUrl;
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.emergencyNumber = emergencyNumber;
        this.customerType = customerType;
        this.status = status;
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Customer(String fullName, String personIdentifier, String bio, String phoneNumber, String emergencyNumber, CustomerType customerType, CustomerStatus status, String email, String encrypted_password, Instant birthdate, String address, String city, String state, String zipCode, Instant createdAt, Instant updatedAt) {
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.emergencyNumber = emergencyNumber;
        this.customerType = customerType;
        this.status = status;
        this.email = email;
        this.encrypted_password = encrypted_password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "person_identifier")
    private String personIdentifier;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "emergency_number")
    private String emergencyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    @Column(name = "email")
    private String email;

    @Column(name = "encrypted_password")
    private String encrypted_password;

    @Column(name = "birthdate")
    private Instant birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

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
        Customer customer = (Customer) o;
        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}