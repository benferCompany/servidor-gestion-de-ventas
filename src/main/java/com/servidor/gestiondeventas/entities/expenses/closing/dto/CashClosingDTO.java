package com.servidor.gestiondeventas.entities.expenses.closing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.servidor.gestiondeventas.entities.balance.Capital;
import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.balance.dto.ActiveDTO;
import com.servidor.gestiondeventas.entities.balance.dto.CapitalDTO;
import com.servidor.gestiondeventas.entities.balance.dto.MovementsDTO;
import com.servidor.gestiondeventas.entities.balance.dto.PassiveDTO;
import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import com.servidor.gestiondeventas.entities.expenses.invoice.dto.InvoiceSupplierDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.dto.SupplierPaymentDTO;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto.OutFlowsDTO;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CashClosingDTO {
    private Long id;
    private ActiveDTO active;
    private PassiveDTO passive;
    private CapitalDTO capital;
    private MovementsDTO movements;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataOpen;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataClose;

    public static CashClosingDTO fromEntity(CashClosing cashClosing){
        if(cashClosing ==null){
            return null;
        }

        CashClosingDTO cashClosingDTO = new CashClosingDTO();
        cashClosingDTO.setId(cashClosing.getId());
        cashClosingDTO.setDataClose(cashClosing.getDateClose());
        cashClosingDTO.setDataOpen(cashClosing.getDateOpen());
        cashClosingDTO.setActive(ActiveDTO.fromEntity(cashClosing.getActive()));
        cashClosingDTO.setCapital(CapitalDTO.fromEntity(cashClosing.getCapital()));
        cashClosingDTO.setPassive(PassiveDTO.fromEntity(cashClosing.getPassive()));
        cashClosingDTO.setMovements(MovementsDTO.fromEntity(cashClosing.getMovements()));
        return cashClosingDTO;
    }
}
