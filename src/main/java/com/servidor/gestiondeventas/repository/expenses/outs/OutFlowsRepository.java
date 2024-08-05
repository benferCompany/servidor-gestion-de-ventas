package com.servidor.gestiondeventas.repository.expenses.outs;

import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OutFlowsRepository extends JpaRepository<OutFlows,Long> {
     OutFlows findFirstByDateAfter(Date date);
}
