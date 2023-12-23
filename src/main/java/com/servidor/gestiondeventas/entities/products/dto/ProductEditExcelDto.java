package com.servidor.gestiondeventas.entities.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductEditExcelDto {
    private String idSupplierOne;
    private double cost_price;
    private double selling_price;
}
