package com.servidor.gestiondeventas.entities.expenses.invoice.dto;

import com.servidor.gestiondeventas.entities.expenses.invoice.DetailProductInvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import lombok.Data;

import javax.persistence.*;

@Data
public class DetailProductInvoiceSupplierDTO {
    private Long id;
    private Long productId;
    private Double quality;
    private String internalCode;
    private String idSupplierOne;
    private String description;
    private Double costPrice;
    private Double price;
    private Double totalPrice;
    private Double totalCostPrice;


    static public DetailProductInvoiceSupplierDTO fromEntity(DetailProductInvoiceSupplier detailProductInvoiceSupplier){
        DetailProductInvoiceSupplierDTO detailProductInvoiceSupplierDTO = new DetailProductInvoiceSupplierDTO();
        detailProductInvoiceSupplierDTO.setProductId(detailProductInvoiceSupplier.getProductId());
        detailProductInvoiceSupplierDTO.setId(detailProductInvoiceSupplier.getId());
        detailProductInvoiceSupplierDTO.setPrice(detailProductInvoiceSupplier.getPrice());
        detailProductInvoiceSupplierDTO.setCostPrice(detailProductInvoiceSupplier.getCostPrice());
        detailProductInvoiceSupplierDTO.setQuality(detailProductInvoiceSupplier.getQuality());
        detailProductInvoiceSupplierDTO.setTotalCostPrice(detailProductInvoiceSupplier.getCostPrice()* detailProductInvoiceSupplier.getQuality());
        detailProductInvoiceSupplierDTO.setTotalPrice(detailProductInvoiceSupplier.getPrice()* detailProductInvoiceSupplier.getQuality());
        detailProductInvoiceSupplierDTO.setDescription(detailProductInvoiceSupplier.getDescription());
        detailProductInvoiceSupplierDTO.setInternalCode(detailProductInvoiceSupplier.getInternalCode());
        detailProductInvoiceSupplierDTO.setIdSupplierOne(detailProductInvoiceSupplier.getIdSupplierOne());

        return  detailProductInvoiceSupplierDTO;

    }
}
