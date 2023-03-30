package com.ggi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "clients")
public class Client extends User {

    @NotNull
    private String accountSettings;

    public String getAccountSettings() {
        return accountSettings;
    }

    public Client setAccountSettings(String accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }
}
