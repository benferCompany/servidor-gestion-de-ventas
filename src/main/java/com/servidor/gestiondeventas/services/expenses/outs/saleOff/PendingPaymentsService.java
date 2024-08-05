package com.servidor.gestiondeventas.services.expenses.outs.saleOff;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.dto.PendingPaymentsDTO;

import java.util.List;

public interface PendingPaymentsService {
    public List<PendingPaymentsDTO> getPendingPayments();

    public PendingPaymentsDTO createPendingPayments(PendingPayments pendingPayments);
    public PendingPaymentsDTO updatePendingPayments(PendingPayments pendingPayments);
    public boolean deletaPendingPayments(Long id);
}
