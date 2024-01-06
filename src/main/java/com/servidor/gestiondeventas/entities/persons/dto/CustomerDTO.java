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
    private String adrress;
    private String email;
    private String phone;
    private String movil_phone;
    private String cuil;
    private double current_account;
    private double discount;
    
    static public CustomerDTO fromEntity(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setAdrress(customer.getAddress());
        dto.setCurrent_account(customer.getCurrent_account());
        dto.setDiscount(customer.getDiscount());
        dto.setLast_name(customer.getLast_name());
        dto.setMovil_phone(customer.getMobil_phone());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        return dto;
    }
}
