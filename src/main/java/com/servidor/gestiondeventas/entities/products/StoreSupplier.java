package com.servidor.gestiondeventas.entities.products;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String idInternal;
    @Column
    String idSupplierOne;
    @Column
    String idSupplierTwo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier_id")
    private Supplier Supplier;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "store_supplier_product",
            joinColumns = @JoinColumn(name = "store_supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
}
