package com.servidor.gestiondeventas.entities.persons;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesPerson extends Person {
    private String shift;
    private String auth;
    private double commission;
}
