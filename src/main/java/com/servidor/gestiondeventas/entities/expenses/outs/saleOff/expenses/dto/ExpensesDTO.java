package com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ExpensesDTO {
    private Long id;
    private String description;
    private Double amount;
    private SalesPersonDTO salesPerson;
    private String paymentType;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public static ExpensesDTO fromEntity(Expenses expenses){
        ExpensesDTO expensesDTO = new ExpensesDTO();

        if(expenses==null){
            return null;
        }

        expensesDTO.setId(expenses.getId());
        expensesDTO.setDescription(expenses.getDescription());
        expensesDTO.setAmount(expenses.getAmount());
        expensesDTO.setDate(expenses.getDate());
        expensesDTO.setSalesPerson(SalesPersonDTO.fromFamily(expenses.getSalesPerson()));
        expensesDTO.setPaymentType(expenses.getPaymentType());

        return expensesDTO;
    }
}
