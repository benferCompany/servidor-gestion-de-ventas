package com.servidor.gestiondeventas.services.expenses.outs.saleOff;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.dto.ExpensesDTO;

import java.util.List;

public interface ExpensesService {
    public List<ExpensesDTO> getExpenses();
    public ExpensesDTO createExpenses(Expenses expenses);
    public boolean deleteExpenses(Long id);
}
