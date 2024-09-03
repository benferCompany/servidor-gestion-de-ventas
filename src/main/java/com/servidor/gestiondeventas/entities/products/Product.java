package com.servidor.gestiondeventas.entities.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import com.servidor.gestiondeventas.entities.products.description.DescriptionProduct;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String idInternal;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private double cost_price;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Store> stores = new ArrayList<>();
    @Column
    private double selling_price;
    @Column
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date creation_date;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<StoreSupplier> storeSuppliers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    private Set<Categories> categories = new HashSet<>();

}
