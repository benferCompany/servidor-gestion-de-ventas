package com.servidor.gestiondeventas.repository.balance;

import com.servidor.gestiondeventas.entities.balance.Passive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PassiveRepository extends JpaRepository<Passive,Long> {
    Optional<Passive> findFirstBy();
    Optional<Passive> findFirstByDateAfter(Date date);

}
