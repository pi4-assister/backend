package com.senac.assister.backend.domain.entity;

import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class User {

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

    @Column(name = "is_legal_person")
    private Boolean isLegalPerson;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    @Column(name = "landline_number")
    private String landlineNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

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

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public User() {
    }

    public User(UUID id, String photoUrl, String fullName, String personIdentifier, Boolean isLegalPerson, String bio, String phoneNumber, CustomerType customerType, CustomerStatus status, String landlineNumber, String email, String password, Instant birthdate, String address, String city, String state, String zipCode, Boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.isLegalPerson = isLegalPerson;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.status = status;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public UUID getId() {
        return id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPersonIdentifier() {
        return personIdentifier;
    }

    public Boolean getLegalPerson() {
        return isLegalPerson;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Boolean getActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}