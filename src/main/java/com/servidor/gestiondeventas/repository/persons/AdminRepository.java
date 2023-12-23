package com.servidor.gestiondeventas.repository.persons;

import com.servidor.gestiondeventas.entities.persons.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
}
