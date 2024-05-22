package com.servidor.gestiondeventas.entities.company;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.products.Store;
import javax.persistence.*;
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
    @Column
    private String fiscal_status;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date business_activity;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Store> stores;
}
