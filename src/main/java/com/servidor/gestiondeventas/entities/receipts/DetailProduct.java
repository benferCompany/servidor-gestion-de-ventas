package com.servidor.gestiondeventas.entities.receipts;

import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Double quality;
    private String internalCode;
    private String description;
    private Double costPrice;
    private Double price;
    private Double totalPrice;
    private Double totalCostPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id")
    private Details details;


}
