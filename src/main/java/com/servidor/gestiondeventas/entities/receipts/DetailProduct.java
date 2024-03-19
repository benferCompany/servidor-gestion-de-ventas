package com.servidor.gestiondeventas.entities.receipts;

import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DetailProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "details_id")
    private Details details;


}
