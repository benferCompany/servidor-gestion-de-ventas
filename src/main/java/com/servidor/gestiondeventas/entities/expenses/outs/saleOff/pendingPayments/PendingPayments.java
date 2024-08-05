package com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PendingPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private Boolean isPaid;
    private String paymentType;

    @OneToOne
    @JoinColumn(name="salesPeson_id")
    private SalesPerson salesPerson;


    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name ="outFlows_id")
    @JsonBackReference
    private OutFlows outFlows;

}
