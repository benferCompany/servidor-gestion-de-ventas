package com.servidor.gestiondeventas.services.balance.impl;

import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.repository.balance.MovementsRepository;
import com.servidor.gestiondeventas.repository.expenses.closing.CashClosingRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.OutFlowsRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.SupplierPaymentRepository;
import com.servidor.gestiondeventas.repository.receipts.DetailsRepository;
import com.servidor.gestiondeventas.services.balance.MovementsService;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import com.servidor.gestiondeventas.services.expenses.outs.OutFlowsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovementsServiceImpl implements MovementsService {
    private final CashClosingService cashClosingService;
    private final DetailsRepository detailsRepository;
    private final OutFlowsRepository outFlowsRepository;
    private final SupplierPaymentRepository supplierPaymentRepository;
    private final MovementsRepository movementsRepository;
    private final OutFlowsService outFlowsService;
    @Override
    @Transactional
    public Movements getMovementsByData() {
        Date date = cashClosingService.getDateCashClosing();
        Optional<Movements> movementsOptional = movementsRepository.findFirstByDateAfter(date);

        Movements movements = movementsOptional.orElseGet(() ->{
            Movements mv = new Movements();
            mv.setDate(new Date());
            mv.setDetails(detailsRepository.findAll());
            mv.setSupplierPayments(supplierPaymentRepository.findAll());

            OutFlows outFlows = new OutFlows();
            outFlows.setDate(new Date());
            outFlows.setMovements(mv);
            outFlowsRepository.save(outFlows);

           return movementsRepository.save(mv);
        }

        );

        if(date !=null){
            movements.setDetails(detailsRepository.findByDateAfter(date));
            movements.setSupplierPayments(supplierPaymentRepository.findByDateAfter(date));
        }


        return movements;
    }
}
