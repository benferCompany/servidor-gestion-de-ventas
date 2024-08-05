package com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.dto.ExpensesDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.dto.PendingPaymentsDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SalesPersonDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OutFlowsDTO {
    private Long id;
    private List<ExpensesDTO> expenses;
    private List<PendingPaymentsDTO> pendingPayments;
    @JsonFormat(pattern= "dd/MM/yyyy HH:mm")
    private Date date;

    static public OutFlowsDTO fromEntity(OutFlows outFlows){
        if(outFlows==null){
            return null;
        }
        OutFlowsDTO outFlowsDTO = new OutFlowsDTO();

        outFlowsDTO.setId(outFlows.getId());
        outFlowsDTO.setExpenses(outFlows.getExpenses().stream().map(ExpensesDTO::fromEntity).collect(Collectors.toList()));
        outFlowsDTO.setPendingPayments(outFlows.getPendingPayments().stream().map(PendingPaymentsDTO::fromEntity).collect(Collectors.toList()));
        outFlowsDTO.setDate(outFlows.getDate());

        return outFlowsDTO;


    }

}
