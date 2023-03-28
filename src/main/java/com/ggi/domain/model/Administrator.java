package com.ggi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "administrators")
@Data
public class Administrator extends User {

    @NotNull
    private String adminName;

    @NotNull
    private String email;
}
