package com.ggi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clients")
@Data
public class Client extends User {

    @NotNull
    private String customerName;

    @NotNull
    private String email;

}
