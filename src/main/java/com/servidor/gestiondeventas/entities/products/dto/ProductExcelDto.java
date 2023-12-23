package com.servidor.gestiondeventas.entities.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public class ProductExcelDto {
    private Long id;
    private String title;
    private String description;
    private double cost_price;
    private double selling_price;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy;yy")
    private Date creation_date;
    
    //Store
    private Long storeId;
    private double stock;
    private double stockMin;
    private double stockMax;
    
    //StoreSupplier
    private Long storeSupplierId;
    private String idInternal;
    private String idSupplierOne;
    private String idSupplierTwo;
    private Long idSupplier;
    

    

}

