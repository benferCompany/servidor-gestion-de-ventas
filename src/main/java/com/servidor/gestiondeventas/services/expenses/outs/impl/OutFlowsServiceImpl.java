package com.servidor.gestiondeventas.services.expenses.outs.impl;

import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto.OutFlowsDTO;
import com.servidor.gestiondeventas.repository.expenses.outs.OutFlowsRepository;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import com.servidor.gestiondeventas.services.expenses.outs.OutFlowsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OutFlowsServiceImpl implements OutFlowsService {
    private final OutFlowsRepository outFlowsRepository;
    private final CashClosingService cashClosingService;
    @Override
    public OutFlows getOutFlows(){
        return outFlowsRepository.findFirstByDateAfter(cashClosingService.getDateCashClosing());
    }

    @Override
    public OutFlowsDTO createOutFlows(OutFlows outFlows){

        return OutFlowsDTO.fromEntity(outFlowsRepository.save(outFlows));
    }
}
