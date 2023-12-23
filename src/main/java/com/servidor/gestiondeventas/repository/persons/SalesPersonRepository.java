package com.servidor.gestiondeventas.repository.persons;

import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson,Long> {
}
