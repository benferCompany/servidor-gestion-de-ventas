package com.servidor.gestiondeventas.repository.balance;

import com.servidor.gestiondeventas.entities.balance.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MovementsRepository extends JpaRepository<Movements,Long> {
    Optional<Movements> findFirstBy();
    Optional<Movements> findFirstByDateAfter(Date date);
}
