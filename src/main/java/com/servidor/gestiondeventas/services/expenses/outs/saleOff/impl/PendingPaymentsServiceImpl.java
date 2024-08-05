package com.servidor.gestiondeventas.services.expenses.outs.saleOff.impl;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.dto.PendingPaymentsDTO;
import com.servidor.gestiondeventas.repository.expenses.outs.saleOff.PendingPaymentRepository;
import com.servidor.gestiondeventas.services.expenses.outs.OutFlowsService;
import com.servidor.gestiondeventas.services.expenses.outs.saleOff.PendingPaymentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PendingPaymentsServiceImpl implements PendingPaymentsService {
    private final PendingPaymentRepository pendingPaymentRepository;
    private final OutFlowsService outFlowsService;
    @Override
    public List<PendingPaymentsDTO> getPendingPayments() {

        return pendingPaymentRepository.findAll().stream().map(PendingPaymentsDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public PendingPaymentsDTO createPendingPayments(PendingPayments pendingPayments) {
        pendingPayments.setOutFlows(outFlowsService.getOutFlows());
        return PendingPaymentsDTO.fromEntity(pendingPaymentRepository.save(pendingPayments));
    }

    @Override
    public PendingPaymentsDTO updatePendingPayments(PendingPayments pendingPayments) {
        pendingPayments.setOutFlows(outFlowsService.getOutFlows());
        return PendingPaymentsDTO.fromEntity(pendingPaymentRepository.save(pendingPayments));
    }

    @Override
    public boolean deletaPendingPayments(Long id) {
        if(pendingPaymentRepository.findById(id).isPresent()){
            pendingPaymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
