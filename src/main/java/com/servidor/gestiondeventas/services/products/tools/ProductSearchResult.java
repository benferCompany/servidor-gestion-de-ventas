package com.servidor.gestiondeventas.services.products.tools;

import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSearchResult {
    private final List<ProductDTO> productDTOList;
    private final Long totalElements;

    public ProductSearchResult(List<ProductDTO> productDTOList, Long totalElements) {
        this.productDTOList = productDTOList;
        this.totalElements = totalElements;
    }

}