package com.servidor.gestiondeventas.entities.receipts;

import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import jakarta.persistence.*;

@MappedSuperclass
public abstract  class Receipts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "salePerson_id")
    private SalesPerson salesPerson;


}