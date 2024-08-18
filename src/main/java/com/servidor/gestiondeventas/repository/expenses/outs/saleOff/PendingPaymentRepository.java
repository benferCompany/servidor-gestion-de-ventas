package com.servidor.gestiondeventas.repository.expenses.outs.saleOff;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PendingPaymentRepository extends JpaRepository<PendingPayments,Long> {
    List<PendingPayments> findByDateAfter(Date date);
    List<PendingPayments>findByDateBetweenAndDescriptionContaining(Date startDate, Date endDate, String description);
}
