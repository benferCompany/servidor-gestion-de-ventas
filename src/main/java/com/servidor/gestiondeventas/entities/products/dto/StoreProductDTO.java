package com.servidor.gestiondeventas.entities.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class StoreProductDTO {
    private Long id;
    private String idInternal;
    private String title;
    private String description;
    private double cost_price;
    private double selling_price;
    private String image;

    static public StoreProductDTO fromFamily(Product product){
        StoreProductDTO dto = new StoreProductDTO();
        dto.setId(product.getId());
        dto.setIdInternal(product.getIdInternal());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setCost_price(product.getCost_price());
        dto.setSelling_price(product.getSelling_price());
        dto.setImage(product.getImage());
        return dto;

    }

}
