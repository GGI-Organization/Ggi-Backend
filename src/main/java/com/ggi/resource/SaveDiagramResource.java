package com.ggi.resource;

import jakarta.validation.constraints.NotNull;

public class SaveDiagramResource {

    @NotNull
    private String status;

    public String getStatus() {
        return status;
    }

    public SaveDiagramResource setStatus(String status) {
        this.status = status;
        return this;
    }
}
