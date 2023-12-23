package com.servidor.gestiondeventas.repository.persons;

import com.servidor.gestiondeventas.entities.persons.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
