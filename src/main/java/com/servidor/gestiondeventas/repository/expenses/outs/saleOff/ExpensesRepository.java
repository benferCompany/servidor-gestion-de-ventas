package com.servidor.gestiondeventas.repository.expenses.outs.saleOff;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findByDateAfter(Date date);
}
