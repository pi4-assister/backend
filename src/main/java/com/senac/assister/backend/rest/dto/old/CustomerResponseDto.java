package com.senac.assister.backend.rest.dto.old;

import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;

import java.time.Instant;

public class CustomerResponseDto {

    private String id;
    private String photoUrl;

    private String fullName;

    private String personIdentifier;

    private Boolean isLegalPerson;

    private String bio;

    private String phoneNumber;

    private Boolean isClient;

    private CustomerType customerType;

    private CustomerStatus status;

    private String landlineNumber;

    private String email;

    private String password;

    private Instant birthdate;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private Instant createdAt;

    private Instant updatedAt;

    public CustomerResponseDto() {
    }

    public CustomerResponseDto(String id, String photoUrl, String fullName, String personIdentifier, Boolean isLegalPerson, String bio, String phoneNumber, Boolean isClient, CustomerType customerType, CustomerStatus status, String landlineNumber, String email, String password, Instant birthdate, String address, String city, String state, String zipCode, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.isLegalPerson = isLegalPerson;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.isClient = isClient;
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
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

    public Boolean getClient() {
        return isClient;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(String id) {
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

    public void setLegalPerson(Boolean legalPerson) {
        isLegalPerson = legalPerson;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setClient(Boolean client) {
        isClient = client;
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

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}