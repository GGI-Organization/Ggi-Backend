package com.ggi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "administrators")
public class Administrator extends Profile {

    @NotNull
    private String authorityLevel;

    @NotNull
    private String[] shareRegister;

    public Administrator(@NotNull String authorityLevel, @NotNull String[] shareRegister) {
        this.authorityLevel = authorityLevel;
        this.shareRegister = shareRegister;
    }

    public Administrator() {
    }

    public String getAuthorityLevel() {
        return authorityLevel;
    }

    public Administrator setAuthorityLevel(String authorityLevel) {
        this.authorityLevel = authorityLevel;
        return this;
    }

    public String[] getShareRegister() {
        return shareRegister;
    }

    public Administrator setShareRegister(String[] shareRegister) {
        this.shareRegister = shareRegister;
        return this;
    }
}
