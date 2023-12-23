package com.servidor.gestiondeventas.entities.persons;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier extends Person {

    private String cuil;
    private String name_bussiness;

}
