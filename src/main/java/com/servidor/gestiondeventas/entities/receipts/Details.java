package com.servidor.gestiondeventas.entities.receipts;

import com.servidor.gestiondeventas.entities.products.Product;
import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "purchase_invoice_id")
    private PurchaseInvoice purchaseInvoice;

    @ManyToMany
    @JoinTable(
            name = "details_products",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
}
