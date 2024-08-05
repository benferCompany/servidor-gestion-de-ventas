package com.servidor.gestiondeventas.repository.expenses.outs;

import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SupplierPaymentRepository extends JpaRepository<SupplierPayment,Long> {
    List<SupplierPayment> findByDateAfter(Date date);
}
