package com.servidor.gestiondeventas.entities.expenses.invoice;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data

public class DetailProductInvoiceSupplier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long productId;
        private Double quality;
        private String internalCode;
        private String idSupplierOne;
        private String description;
        private Double costPrice;
        private Double price;
        private Double totalPrice;
        private Double totalCostPrice;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "invoice_supplier_id")
        private InvoiceSupplier invoiceSupplier;



}
