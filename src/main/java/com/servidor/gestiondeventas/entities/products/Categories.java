package com.servidor.gestiondeventas.entities.products;

import com.servidor.gestiondeventas.controllers.products.ProductController;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Categories parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<Categories> subCategories = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

    )
    private Set<Product> products = new HashSet<>();


}
