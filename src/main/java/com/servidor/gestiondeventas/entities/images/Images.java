package com.servidor.gestiondeventas.entities.images;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String value;


}
