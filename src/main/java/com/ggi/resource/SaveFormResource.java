package com.ggi.resource;

import jakarta.validation.constraints.NotNull;

public class SaveFormResource {

    @NotNull
    private String type;

    public String getType() {
        return type;
    }

    public SaveFormResource setType(String type) {
        this.type = type;
        return this;
    }
}
