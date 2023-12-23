package com.servidor.gestiondeventas.entities.company.dto;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.products.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CompanyDTO {
    private Long id;
    private String name;
    private String business_name;
    private String cuit;
    private String address;
    private Date business_activity;
    private List<Long> storesId;

    public static CompanyDTO fromEntity(Company company) {
        CompanyDTO dto = new CompanyDTO();

        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setBusiness_name(company.getBusiness_name());
        dto.setCuit(company.getCuit());
        dto.setAddress(company.getAddress());
        dto.setBusiness_activity(company.getBusiness_activity());
        if (dto.getStoresId() != null) {
            dto.setStoresId(company.getStores().stream()
                    .map(Store::getId).collect(Collectors.toList()));
        }

        return dto;
    }
}
