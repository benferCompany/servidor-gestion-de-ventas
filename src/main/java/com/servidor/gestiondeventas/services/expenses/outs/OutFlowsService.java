package com.servidor.gestiondeventas.services.expenses.outs;

import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto.OutFlowsDTO;

import java.util.List;

public interface OutFlowsService {
    public OutFlows getOutFlows();
    public OutFlowsDTO createOutFlows(OutFlows outFlows);
}
