package com.servidor.gestiondeventas.entities.persons.dto;

import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import lombok.Data;

@Data
public class SalesPersonDTO {
    private String shift;
    private String auth;
    private double comission;

    private Long id;
    private String name;
    private String last_name;
    private String address;
    private String email;
    private String phone;
    private String mobile_phone;
    private String idPersonal;

    static public SalesPersonDTO fromFamily(SalesPerson salesPerson){
        SalesPersonDTO salesPersonDTO = new SalesPersonDTO();

        salesPersonDTO.setIdPersonal(salesPerson.getIdPersonal());
        salesPersonDTO.setId(salesPerson.getId());
        salesPersonDTO.setAuth(salesPerson.getAuth());
        salesPersonDTO.setEmail(salesPerson.getEmail());
        salesPersonDTO.setComission(salesPerson.getCommission());
        salesPersonDTO.setLast_name(salesPerson.getLast_name());
        salesPersonDTO.setAddress(salesPerson.getAddress());
        salesPersonDTO.setName(salesPerson.getName());
        salesPersonDTO.setMobile_phone(salesPerson.getMobile_phone());
        salesPersonDTO.setShift(salesPerson.getShift());
        salesPersonDTO.setPhone(salesPerson.getPhone());
        return salesPersonDTO;
    }

}
