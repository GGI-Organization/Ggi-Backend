package com.ggi.resource;

import jakarta.validation.constraints.NotNull;

public class SaveFormManagerResource {

    @NotNull
    private String status;

    public String getStatus() {
        return status;
    }

    public SaveFormManagerResource setStatus(String status) {
        this.status = status;
        return this;
    }
}
