package com.servidor.gestiondeventas.entities.products.description;

import com.servidor.gestiondeventas.entities.products.Product;
import lombok.Data;

import javax.persistence.Lob;

@Data
public class DescriptionProductDTO {
    private Long id;
    private Product product;
    private String content;

    public DescriptionProductDTO fromEntity(DescriptionProduct descriptionProduct){
        DescriptionProductDTO dto = new DescriptionProductDTO();

        if(product!=null){
            dto.setProduct(descriptionProduct.getProduct());
        }
        dto.setId(descriptionProduct.getId());
        dto.setContent(descriptionProduct.getContent());
        return dto;
    }

}
