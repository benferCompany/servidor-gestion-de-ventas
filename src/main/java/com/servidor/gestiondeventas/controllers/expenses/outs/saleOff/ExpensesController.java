package com.servidor.gestiondeventas.controllers.expenses.outs.saleOff;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.dto.ExpensesDTO;
import com.servidor.gestiondeventas.services.expenses.outs.saleOff.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
@CrossOrigin(origins ="*")
public class ExpensesController {
    private final ExpensesService expensesService;
    @GetMapping
    public ResponseEntity<List<ExpensesDTO>> getExpenses(){
        return new ResponseEntity<>(expensesService.getExpenses(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpensesDTO> createExpenses(@RequestBody Expenses expenses){
        return new ResponseEntity<>(expensesService.createExpenses(expenses), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public boolean deleteExpenses(@PathVariable Long id){
        return expensesService.deleteExpenses(id);
    }
}
