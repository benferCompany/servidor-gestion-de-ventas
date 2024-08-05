package com.servidor.gestiondeventas.services.expenses.outs.saleOff.impl;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.dto.ExpensesDTO;
import com.servidor.gestiondeventas.repository.expenses.outs.OutFlowsRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.saleOff.ExpensesRepository;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import com.servidor.gestiondeventas.services.expenses.outs.saleOff.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final OutFlowsRepository outFlowsRepository;
    private final CashClosingService cashClosingService;
    @Override
    public List<ExpensesDTO> getExpenses(){
        return expensesRepository.findAll().stream().map(ExpensesDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ExpensesDTO createExpenses(Expenses expenses){
        expenses.setOutFlows(outFlowsRepository.findFirstByDateAfter(cashClosingService.getDateCashClosing()));
        return ExpensesDTO.fromEntity(expensesRepository.save(expenses));
    }

    @Override
    public boolean deleteExpenses(Long id){
        if(expensesRepository.findById(id).isPresent()){
            expensesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
