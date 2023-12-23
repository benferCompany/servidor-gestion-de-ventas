package com.servidor.gestiondeventas.repository.persons;

import com.servidor.gestiondeventas.entities.persons.Supplier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByCuil(String cuit);
}
