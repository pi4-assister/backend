package com.senac.assister.backend.rest.dto.customer;

import java.util.UUID;

public class UploadPictureResponse {

    private UUID costumerId;

    private String url;

    public UploadPictureResponse() {
    }

    public UploadPictureResponse(UUID costumerId, String url) {
        this.costumerId = costumerId;
        this.url = url;
    }

    public UUID getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(UUID costumerId) {
        this.costumerId = costumerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
