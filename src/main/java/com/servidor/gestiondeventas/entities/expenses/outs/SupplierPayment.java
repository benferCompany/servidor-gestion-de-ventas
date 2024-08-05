package com.servidor.gestiondeventas.entities.expenses.outs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.balance.Passive;
import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import com.servidor.gestiondeventas.entities.persons.Supplier;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SupplierPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "sales_person_id")
    private SalesPerson salesPerson;

    @JsonFormat(pattern= "dd/MM/yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "invoice_supplier_id")
    private InvoiceSupplier invoiceSupplier;
    private String paymentType;
    private Double payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id")
    private Movements movements;



}
