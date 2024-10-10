package com.servidor.gestiondeventas.entities.receipts.dto;

import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import lombok.Data;


@Data
public class DetailProductsDto {
    private Long id;

    private Double quality;
    private Long productId;
    private String internalCode;
    private String description;
    private Double price;
    private Double costPrice;
    private Double totalCostPrice;
    private Double totalPrice;
   

    public static DetailProductsDto fromEntity(DetailProduct detailProducts){
        DetailProductsDto detailProductsDto = new DetailProductsDto();

        detailProductsDto.setDescription(detailProducts.getDescription());
        detailProductsDto.setInternalCode(detailProducts.getInternalCode());
        detailProductsDto.setId(detailProducts.getId());
        detailProductsDto.setQuality(detailProducts.getQuality());
        detailProductsDto.setPrice(detailProducts.getPrice());
        detailProductsDto.setCostPrice(detailProducts.getCostPrice());
        detailProductsDto.setTotalPrice(detailProducts.getPrice() * detailProducts.getQuality());
        detailProductsDto.setTotalCostPrice(detailProducts.getCostPrice() * detailProducts.getQuality());
        detailProductsDto.setProductId(detailProducts.getProductId());
        return detailProductsDto;
    }
}
