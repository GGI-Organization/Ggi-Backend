package com.ggi.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clients")
@Data
public class Client extends User {
    @NotNull
    private String customerName;
    @NotNull
    private String email;
    @NotNull
    private String creditCardInfo;
}
