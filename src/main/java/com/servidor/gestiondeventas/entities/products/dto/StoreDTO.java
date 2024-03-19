package com.servidor.gestiondeventas.entities.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.company.dto.CompanyDTO;
import com.servidor.gestiondeventas.entities.products.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StoreDTO {
    private Long id;
    private CompanyDTO company;
    private StoreProductDTO product;
    private double stock;
    private double stock_min;
    private double stock_max;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date last_modication;

    public static StoreDTO fromEntity(Store store) {
        StoreDTO dto = new StoreDTO();
        if (store.getId() != null) {
            dto.setId(store.getId());
        }
        dto.setId(store.getId());
        if (store.getCompany() != null) {
            dto.setCompany(CompanyDTO.fromEntity(store.getCompany()));
        }
        dto.setStock(store.getStock());
        dto.setProduct(StoreProductDTO.fromFamily(store.getProduct()));
        dto.setStock_max(store.getStock_max());
        dto.setStock_min(store.getStock_min());
        dto.setLast_modication(store.getLast_modication());

        return dto;
    }
}
