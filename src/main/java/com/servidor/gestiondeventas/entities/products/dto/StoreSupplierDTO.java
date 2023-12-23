package com.servidor.gestiondeventas.entities.products.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.StoreSupplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreSupplierDTO {
    private Long id;
    private String idInternal;
    private String idSupplierOne;
    private String idSupplierTwo;
    private Supplier supplier;
    private List<Long> idProduct = new ArrayList<>();

    public static StoreSupplierDTO fromStoreSupplierDTO(StoreSupplier storeSupplier) {
        StoreSupplierDTO dto = new StoreSupplierDTO();

        dto.setId(storeSupplier.getId());
        dto.setIdProduct(
                storeSupplier.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()));
        dto.setSupplier(storeSupplier.getSupplier());
        dto.setIdInternal(storeSupplier.getIdInternal());
        dto.setIdSupplierOne(storeSupplier.getIdSupplierOne());
        dto.setIdSupplierTwo(storeSupplier.getIdSupplierTwo());


        return dto;
    }
}
