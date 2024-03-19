package com.servidor.gestiondeventas.entities.persons.dto;

import com.servidor.gestiondeventas.entities.persons.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String last_name;
    private String address;
    private String email;
    private String phone;
    private String mobile_phone;
    private String idPersonal;
    private String fiscal_status;
    private double current_account;
    private double discount;
    
    static public CustomerDTO fromEntity(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setAddress(customer.getAddress());
        dto.setCurrent_account(customer.getCurrent_account());
        dto.setDiscount(customer.getDiscount());
        dto.setLast_name(customer.getLast_name());
        dto.setMobile_phone(customer.getMobile_phone());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setIdPersonal(customer.getIdPersonal());
        dto.setFiscal_status(customer.getFiscal_status());
        return dto;
    }
}
