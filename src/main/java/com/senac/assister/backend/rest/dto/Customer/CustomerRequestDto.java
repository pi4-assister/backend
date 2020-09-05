package com.senac.assister.backend.rest.dto.Customer;

import com.senac.assister.backend.domain.enumeration.customer.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.customer.CustomerType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

public class CustomerRequestDto {
    @NotNull(message = "photoUrl field must be sent.")
    @Size(min = 1, max = 255, message = "photoUrl must be between 1 and 255 characters")
    private String photoUrl;

    @NotNull(message = "fullName field must be sent.")
    @Size(min = 1, max = 255, message = "fullName must be between 1 and 255 characters")
    private String fullName;

    @NotNull
    @Size(min = 1, max = 45, message = "personIdentifier must be between 1 and 45 characters")
    private String personIdentifier;

    @NotNull(message = "isLegalPerson field must be sent.")
    private boolean isLegalPerson;

    @Size(min = 1, max = 500, message = "bio must be between 1 and 500 characters")
    private String bio;

    @NotNull(message = "phoneNumber field must be sent.")
    @Size(min = 1, max = 45, message = "phoneNumber must be between 1 and 45 characters")
    private String phoneNumber;

    @NotNull(message = "isClient field must be sent.")
    private boolean isClient;

    private CustomerType customerType;

    private CustomerStatus status;

    private String landlineNumber;

    @NotNull(message = "email field must be sent.")
    @Email(message = "email should be a valid e-mail.")
    @Size(min = 1, max = 255, message = "email must be between 1 and 255 characters")
    private String email;

    @NotNull(message = "password field must be sent.")
    @Size(min = 1, max = 255, message = "password must be between 1 and 255 characters")
    private String password;

    @NotNull(message = "birthdate field must be sent.")
    private Instant birthdate;

    @NotNull(message = "address field must be sent.")
    @Size(min = 1, max = 255, message = "address must be between 1 and 255 characters")
    private String address;

    @NotNull(message = "city field must be sent.")
    @Size(min = 1, max = 255, message = "city must be between 1 and 255 characters")
    private String city;

    @NotNull(message = "state field must be sent.")
    @Size(min = 1, max = 255, message = "state must be between 1 and 255 characters")
    private String state;

    @NotNull(message = "zipCode field must be sent.")
    @Size(min = 1, max = 45, message = "zipCode must be between 1 and 255 characters")
    private String zipCode;

    public CustomerRequestDto() {
    }

    public CustomerRequestDto(String photoUrl, String fullName, String personIdentifier, boolean isLegalPerson, String bio, String phoneNumber, boolean isClient, CustomerType customerType, CustomerStatus status, String landlineNumber, String email, String password, Instant birthdate, String address, String city, String state, String zipCode) {
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

    public boolean getClient() {
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

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
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

    public void build() {
        if (this.isClient) {
            this.customerType = CustomerType.CLIENT;
        } else {
            this.customerType = CustomerType.PARTNER;
        }

        this.status = CustomerStatus.HIRED;
    }
}
