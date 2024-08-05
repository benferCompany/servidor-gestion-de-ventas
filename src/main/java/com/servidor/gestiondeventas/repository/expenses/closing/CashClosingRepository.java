package com.servidor.gestiondeventas.repository.expenses.closing;

import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  CashClosingRepository extends JpaRepository <CashClosing,Long> {

    Optional<CashClosing> findTopByOrderByDateCloseDesc();
}
