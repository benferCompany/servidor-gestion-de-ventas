package com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class PendingPaymentsDTO {
    private Long id;
    private String description;
    private Double amount;
    private Boolean isPaid;
    private String paymentType;
    private SalesPersonDTO salesPerson;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Argentina/Buenos_Aires")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public static PendingPaymentsDTO fromEntity(PendingPayments pendingPayments){
        PendingPaymentsDTO pendingPaymentsDTO = new PendingPaymentsDTO();
        if(pendingPayments ==null){
            return  null;
        }

        pendingPaymentsDTO.setId(pendingPayments.getId());
        pendingPaymentsDTO.setDescription(pendingPayments.getDescription());
        pendingPaymentsDTO.setAmount(pendingPayments.getAmount());
        pendingPaymentsDTO.setIsPaid(pendingPayments.getIsPaid());
        pendingPaymentsDTO.setDate(pendingPayments.getDate());
        pendingPaymentsDTO.setSalesPerson(SalesPersonDTO.fromFamily(pendingPayments.getSalesPerson()));
        pendingPaymentsDTO.setPaymentType(pendingPayments.getPaymentType());

        return pendingPaymentsDTO;
    }
}
