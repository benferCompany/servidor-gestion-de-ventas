package com.servidor.gestiondeventas.repository.receipts;

import com.servidor.gestiondeventas.entities.receipts.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
