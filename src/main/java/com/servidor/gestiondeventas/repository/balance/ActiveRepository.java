package com.servidor.gestiondeventas.repository.balance;

import com.servidor.gestiondeventas.entities.balance.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ActiveRepository extends JpaRepository<Active,Long> {
    Optional<Active> findFirstByDateAfter(Date date);
    Optional<Active> findFirstBy();
}
