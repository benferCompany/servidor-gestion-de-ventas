package com.servidor.gestiondeventas.entities.balance.dto;

import com.servidor.gestiondeventas.entities.balance.Passive;
import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.invoice.dto.InvoiceSupplierDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.expenses.outs.dto.SupplierPaymentDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto.OutFlowsDTO;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PassiveDTO {
    private Long id;
    private double account_suppliers;
    private Double outFlows;
    private Double pending_payments;

    private Double total;

    public static PassiveDTO fromEntity(Passive passive){
        if (passive == null) {
            return null;
        }
        PassiveDTO passiveDTO = new PassiveDTO();
        passiveDTO.setTotal(passive.getTotal());
        passiveDTO.setId(passive.getId());
        passiveDTO.setOutFlows(passive.getOutFlows());
        passiveDTO.setPending_payments(passive.getPending_payments());
        passiveDTO.setAccount_suppliers(passive.getAccount_suppliers());
        return passiveDTO;
    }

}
