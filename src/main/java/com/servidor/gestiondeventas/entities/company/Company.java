package com.servidor.gestiondeventas.entities.company;


import com.servidor.gestiondeventas.entities.products.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String business_name;
    @Column
    private String cuit;
    @Column
    private String address;
    @Temporal(TemporalType.DATE)
    @Column
    private Date business_activity;
    @OneToMany(mappedBy = "company")
    private List<Store> stores;
}
