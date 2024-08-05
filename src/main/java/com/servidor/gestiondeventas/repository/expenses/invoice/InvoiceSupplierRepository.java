package com.servidor.gestiondeventas.repository.expenses.invoice;

import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceSupplierRepository extends JpaRepository<InvoiceSupplier,Long> {
    List<InvoiceSupplier> findByDateAfter(Date date);
}
