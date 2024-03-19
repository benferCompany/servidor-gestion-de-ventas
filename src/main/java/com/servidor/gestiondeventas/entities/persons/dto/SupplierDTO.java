package com.servidor.gestiondeventas.entities.persons.dto;

import com.servidor.gestiondeventas.entities.persons.Supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDTO {
    private Long id;
    private String name;
    private String last_name;
    private String address;
    private String email;
    private String phone;
    private String movil_phone;
    private String idPersonal;
    private String name_bussiness;

    static public SupplierDTO fromSupplierDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setLast_name(supplier.getLast_name());
        dto.setAddress(supplier.getAddress());
        dto.setEmail(supplier.getEmail());
        dto.setPhone(supplier.getPhone());
        dto.setMovil_phone(supplier.getMobile_phone());
        dto.setIdPersonal(supplier.getIdPersonal());
        dto.setName_bussiness(supplier.getName_bussiness());

        return dto;
    }

}
