package com.senac.assister.backend.rest.dto.customer;

import java.util.UUID;

public class UploadPictureResponse {

    private UUID customer_id;

    private String url;

    public UploadPictureResponse() {
    }

    public UploadPictureResponse(UUID customer_id, String url) {
        this.customer_id = customer_id;
        this.url = url;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
