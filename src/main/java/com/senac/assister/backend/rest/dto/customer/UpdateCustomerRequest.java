package com.senac.assister.backend.rest.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;

public class UpdateCustomerRequest {

    private static final ModelMapper mapper = new ModelMapper();

    public UpdateCustomerRequest() {
    }

    public UpdateCustomerRequest(@Size(min = 0, max = 0, message = "testando") UUID id, @NotNull(message = "photoUrl field must be sent.") @Size(min = 1, max = 255, message = "photoUrl must be between 1 and 255 characters") String photoUrl, @NotNull(message = "fullName field must be sent.") @Size(min = 1, max = 255, message = "fullName must be between 1 and 255 characters") String fullName, @NotNull @Size(min = 1, max = 45, message = "personIdentifier must be between 1 and 45 characters") String personIdentifier, @NotNull(message = "isLegalPerson field must be sent.") boolean isLegalPerson, @Size(min = 1, max = 500, message = "bio must be between 1 and 500 characters") String bio, @NotNull(message = "phoneNumber field must be sent.") @Size(min = 1, max = 45, message = "phoneNumber must be between 1 and 45 characters") String phoneNumber, @NotNull(message = "customerType field must be sent.") CustomerType customerType, String landlineNumber, @NotNull(message = "email field must be sent.") @Email(message = "email should be a valid e-mail.") @Size(min = 1, max = 255, message = "email must be between 1 and 255 characters") String email, @NotNull(message = "password field must be sent.") @Size(min = 1, max = 255, message = "password must be between 1 and 255 characters") String password, @NotNull(message = "birthdate field must be sent.") Instant birthdate, @NotNull(message = "address field must be sent.") @Size(min = 1, max = 255, message = "address must be between 1 and 255 characters") String address, @NotNull(message = "city field must be sent.") @Size(min = 1, max = 255, message = "city must be between 1 and 255 characters") String city, @NotNull(message = "state field must be sent.") @Size(min = 1, max = 255, message = "state must be between 1 and 255 characters") String state, @NotNull(message = "zipCode field must be sent.") @Size(min = 1, max = 45, message = "zipCode must be between 1 and 255 characters") String zipCode) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.isLegalPerson = isLegalPerson;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @JsonIgnore
    private UUID id;

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

    @NotNull(message = "customerType field must be sent.")
    private CustomerType customerType;

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

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public static Customer convertToEntity(UpdateCustomerRequest request) {
        return mapper.map(request, Customer.class);
    }
}
