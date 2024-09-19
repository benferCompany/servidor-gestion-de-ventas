package com.servidor.gestiondeventas.entities.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.products.Categories;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.description.DescriptionProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductShopDTO {
    private Long id;
    private String idInternal;
    private String title;
    private double selling_price;
    private List<StoreDTO> stores;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date creation_date;
    private List<CategoriesDTO> categories = new ArrayList<>();

    public static ProductShopDTO fromEntity(Product product) {
        ProductShopDTO dto = new ProductShopDTO();
        if (product.getId() != null) {
            dto.setId(product.getId());
        }
        dto.setIdInternal(product.getIdInternal());
        dto.setTitle(product.getTitle());
        dto.setSelling_price(product.getSelling_price());
        dto.setImage(product.getImage());
        dto.setCreation_date(product.getCreation_date());
        if (product.getStores() != null) {
            dto.setStores(product.getStores().stream().map(StoreDTO::fromEntity).collect(Collectors.toList()));
        }
        if (product.getCategories() != null) {
            dto.setCategories(product.getCategories().stream()
                    .map(CategoriesDTO::fromEntity).collect(Collectors.toList()));
        }
        return dto;
    }
}
