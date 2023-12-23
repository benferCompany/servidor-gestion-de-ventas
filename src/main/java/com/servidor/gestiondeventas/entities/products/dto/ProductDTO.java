package com.servidor.gestiondeventas.entities.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private double cost_price;
    private double selling_price;
    private List<StoreDTO> stores;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date creation_date;
    private List<StoreSupplierDTO> storeSuppliers = new ArrayList<>();

    public static ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        if (product.getId() != null) {
            dto.setId(product.getId());
        }
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setCost_price(product.getCost_price());
        dto.setSelling_price(product.getSelling_price());
        dto.setImage(product.getImage());
        dto.setCreation_date(product.getCreation_date());
        if (product.getStores() != null) {
            dto.setStores(product.getStores().stream().map(StoreDTO::fromEntity).collect(Collectors.toList()));
        }
        if (product.getStoreSuppliers() != null) {
            dto.setStoreSuppliers(product.getStoreSuppliers().stream()
                    .map(StoreSupplierDTO::fromStoreSupplierDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
