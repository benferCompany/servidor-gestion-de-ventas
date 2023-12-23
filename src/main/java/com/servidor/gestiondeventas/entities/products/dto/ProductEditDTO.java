package com.servidor.gestiondeventas.entities.products.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEditDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private double cost_price;
    private double selling_price;
    private double stock;
    private double stockMin;
    private double stockMax;
    private Long storeId;
    private Long companyId;


}
