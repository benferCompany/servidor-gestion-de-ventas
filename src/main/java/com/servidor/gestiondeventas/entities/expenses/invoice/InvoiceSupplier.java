package com.servidor.gestiondeventas.entities.expenses.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.balance.Passive;
import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class InvoiceSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberInvoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "invoiceSupplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailProductInvoiceSupplier> detailProductInvoiceSuppliers = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_person_id")
    private SalesPerson salesPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String fiscalStatus;
    private String paymentType;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date dueDate;

    @OneToMany(mappedBy = "invoiceSupplier", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true )
    private List<SupplierPayment> supplierPayments = new ArrayList<>();

    private Double total;


}
