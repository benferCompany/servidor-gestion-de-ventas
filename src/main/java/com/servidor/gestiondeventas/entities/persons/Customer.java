package com.servidor.gestiondeventas.entities.persons;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer extends Person {
    private double current_account;
    private double discount;
}
