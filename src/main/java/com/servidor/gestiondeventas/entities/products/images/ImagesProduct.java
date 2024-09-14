package com.servidor.gestiondeventas.entities.products.images;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ImagesProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProd;
    @ElementCollection
    @CollectionTable(name = "images_product_src", joinColumns = @JoinColumn(name = "images_product_id"))
    @Column(name = "src")
    private List<String> src = new ArrayList<>();

}
