package com.servidor.gestiondeventas.repository.receipts.AFIP;

import com.servidor.gestiondeventas.entities.receipts.AFIP.AuthAFIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthAFIPRepository extends JpaRepository<AuthAFIP,Long> {
    AuthAFIP findFirstByCuit(String cuit);
}
