package com.senac.assister.backend.rest.dto.customer;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import org.modelmapper.ModelMapper;

import java.time.Instant;

public class CustomerResponse {

    private static final ModelMapper mapper = new ModelMapper();

    private String id;

    private String photoUrl;

    private String fullName;

    private String personIdentifier;

    private boolean isLegalPerson;

    private String bio;

    private String phoneNumber;

    private CustomerType customerType;

    private CustomerStatus customerStatus;

    private String email;

    private Instant birthdate;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private Instant createdAt;

    private Instant updatedAt;

    public CustomerResponse() {
    }

    public CustomerResponse(String id, String photoUrl, String fullName, String personIdentifier, boolean isLegalPerson, String bio, String phoneNumber, CustomerType customerType, CustomerStatus customerStatus, String email, Instant birthdate, String address, String city, String state, String zipCode, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.isLegalPerson = isLegalPerson;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.customerStatus = customerStatus;
        this.email = email;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPersonIdentifier() {
        return personIdentifier;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public static CustomerResponse convertToDto(Customer customer) {
        return mapper.map(customer, CustomerResponse.class);
    }
}
