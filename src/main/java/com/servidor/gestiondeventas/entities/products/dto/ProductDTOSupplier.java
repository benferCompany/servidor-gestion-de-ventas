package com.servidor.gestiondeventas.entities.products.dto;

import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDTOSupplier {
    private Long id;
    private String idInternal;
    private String title;
    private String description;
    private String image;
    private double cost_price;
    private double selling_price;
    private List<StoreDTO> stores;

    public static ProductDTOSupplier fromFamily(Product product){
        ProductDTOSupplier dto = new ProductDTOSupplier();
        if (product.getId() != null) {
            dto.setId(product.getId());
        }
        dto.setIdInternal(product.getIdInternal());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setCost_price(product.getCost_price());
        dto.setSelling_price(product.getSelling_price());
        dto.setImage(product.getImage());
        if (product.getStores() != null) {
            dto.setStores(product.getStores().stream().map(StoreDTO::fromEntity).collect(Collectors.toList()));
        }
        return dto;

    }


}
