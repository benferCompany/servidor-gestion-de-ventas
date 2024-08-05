package com.servidor.gestiondeventas.entities.balance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Passive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double account_suppliers;
    private Double outFlows;
    private Double pending_payments;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Double total;






}
