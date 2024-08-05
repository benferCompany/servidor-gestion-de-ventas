package com.servidor.gestiondeventas.services.expenses.closing;

import com.servidor.gestiondeventas.entities.expenses.closing.dto.CashClosingDTO;

import java.util.Date;
import java.util.List;

public interface CashClosingService {
    public List<CashClosingDTO> getCashClosing();

    public CashClosingDTO getCashClosingByDate();
    public Date getDateCashClosing();
}
