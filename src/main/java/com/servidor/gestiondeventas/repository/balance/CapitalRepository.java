package com.servidor.gestiondeventas.repository.balance;

import com.servidor.gestiondeventas.entities.balance.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface CapitalRepository extends JpaRepository<Capital,Long> {
    Optional<Capital> findFirstBy();
    Optional<Capital> findFirstByDateAfter(Date date);
    Optional<Capital> findTopByOrderByDateDesc();
}
