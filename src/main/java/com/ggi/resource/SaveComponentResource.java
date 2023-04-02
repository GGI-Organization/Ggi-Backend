package com.ggi.resource;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

public class SaveComponentResource {

    @NotNull
    private String tag;

    @NotNull
    @Lob
    private String description;

    public String getTag() {
        return tag;
    }

    public SaveComponentResource setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveComponentResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
