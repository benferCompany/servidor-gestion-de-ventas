package com.servidor.gestiondeventas.entities.receipts.dto;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.receipts.DetailProduct;
import lombok.Data;

@Data
public class DetailProductsDto {
    private Long id;
    private ProductDTO product;
    private double quality;
    private double total;

    public static DetailProductsDto fromEntity(DetailProduct detailProducts){
        DetailProductsDto detailProductsDto = new DetailProductsDto();

        detailProductsDto.setProduct(ProductDTO.fromEntity(detailProducts.getProduct()));
        detailProductsDto.setId(detailProducts.getId());
        detailProductsDto.setQuality(detailProducts.getQuality());
        detailProductsDto.setTotal(detailProducts.getProduct().getSelling_price()*detailProducts.getQuality());
        return detailProductsDto;
    }
}
