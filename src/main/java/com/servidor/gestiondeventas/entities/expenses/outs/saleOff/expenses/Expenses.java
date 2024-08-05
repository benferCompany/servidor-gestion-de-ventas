package com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private String paymentType;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne
    @JoinColumn(name="salesPeson_id")
    private SalesPerson salesPerson;

    @ManyToOne
    @JoinColumn(name="outFlows_id")
    @JsonBackReference
    private OutFlows outFlows;


}
