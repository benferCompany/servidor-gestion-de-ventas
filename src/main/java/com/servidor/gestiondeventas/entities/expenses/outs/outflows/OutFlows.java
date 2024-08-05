package com.servidor.gestiondeventas.entities.expenses.outs.outflows;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class OutFlows {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "outFlows", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<PendingPayments> pendingPayments;

    @OneToMany(mappedBy = "outFlows", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<Expenses> expenses;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id")
    @JsonBackReference
    private Movements movements;


}

