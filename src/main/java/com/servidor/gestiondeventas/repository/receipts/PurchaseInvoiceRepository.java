package com.servidor.gestiondeventas.repository.receipts;

import com.servidor.gestiondeventas.entities.receipts.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long> {
}
