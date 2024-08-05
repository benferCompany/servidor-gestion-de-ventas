package com.servidor.gestiondeventas.repository.receipts;

import com.servidor.gestiondeventas.entities.receipts.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
    List<Details> findByDateAfter(Date date);
}
