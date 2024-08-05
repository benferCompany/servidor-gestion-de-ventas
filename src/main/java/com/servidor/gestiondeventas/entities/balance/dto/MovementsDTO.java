package com.servidor.gestiondeventas.entities.balance.dto;

import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.expenses.outs.dto.SupplierPaymentDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto.OutFlowsDTO;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MovementsDTO {
    private Long id;
    private List<DetailsDto> details;
    private List<SupplierPaymentDTO> supplierPayments;
    private OutFlowsDTO outFlows;
    public static MovementsDTO fromEntity(Movements movements){
        if(movements ==null){
            return null;
        }
        MovementsDTO movementsDTO = new MovementsDTO();
        movementsDTO.setDetails(movements.getDetails().stream().map(DetailsDto::fromEntity).collect(Collectors.toList()));
        movementsDTO.setSupplierPayments(movements.getSupplierPayments().stream().map(SupplierPaymentDTO::fromEntity).collect(Collectors.toList()));
        movementsDTO.setOutFlows(OutFlowsDTO.fromEntity(movements.getOutFlows()));
movementsDTO.setId(movements.getId());
        return movementsDTO;
    }
}
