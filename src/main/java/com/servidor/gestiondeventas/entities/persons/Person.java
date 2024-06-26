package com.servidor.gestiondeventas.entities.persons;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String IdPersonal;
    @Column
    private String name;
    @Column
    private String last_name;
    @Column
    private String address;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String mobile_phone;

}
