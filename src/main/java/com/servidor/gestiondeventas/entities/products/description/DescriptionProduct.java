package com.servidor.gestiondeventas.entities.products.description;

import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DescriptionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;



    private String content;


}

