package com.senac.assister.backend.domain.entity;

import com.senac.assister.backend.domain.enumeration.customer.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.customer.CustomerType;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

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
    private boolean isLegalPerson;

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
    private boolean active;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public Customer() {
    }

    public Customer(UUID id, String photoUrl, String fullName, String personIdentifier, boolean isLegalPerson, String bio, String phoneNumber, CustomerType customerType, CustomerStatus status, String landlineNumber, String email, String password, Instant birthdate, String address, String city, String state, String zipCode, boolean active, Instant createdAt, Instant updatedAt) {
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

    public boolean getLegalPerson() {
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

    public boolean getActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier;
    }

    public boolean isLegalPerson() {
        return isLegalPerson;
    }

    public void setLegalPerson(boolean legalPerson) {
        isLegalPerson = legalPerson;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return email.equals(customer.email) &&
                password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}