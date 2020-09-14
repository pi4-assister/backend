package com.senac.assister.backend.rest.dto.customer;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import org.modelmapper.ModelMapper;

import java.time.Instant;

public class CreateCustomerResponse {

    private static final ModelMapper mapper = new ModelMapper();

    private String id;

    private String photoUrl;

    private Instant createdAt;


    public CreateCustomerResponse() {
    }

    public CreateCustomerResponse(String id, String photoUrl, Instant createdAt) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public static CreateCustomerResponse convertToDto(Customer customer) {
        return mapper.map(customer, CreateCustomerResponse.class);
    }
}
