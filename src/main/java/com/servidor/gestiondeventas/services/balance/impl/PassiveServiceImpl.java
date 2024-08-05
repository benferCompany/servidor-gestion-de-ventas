package com.servidor.gestiondeventas.services.balance.impl;

import com.servidor.gestiondeventas.entities.balance.Passive;
import com.servidor.gestiondeventas.entities.expenses.invoice.InvoiceSupplier;
import com.servidor.gestiondeventas.entities.expenses.outs.SupplierPayment;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.expenses.Expenses;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import com.servidor.gestiondeventas.repository.balance.PassiveRepository;
import com.servidor.gestiondeventas.repository.expenses.invoice.InvoiceSupplierRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.OutFlowsRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.SupplierPaymentRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.saleOff.ExpensesRepository;
import com.servidor.gestiondeventas.repository.expenses.outs.saleOff.PendingPaymentRepository;
import com.servidor.gestiondeventas.services.balance.PassiveService;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassiveServiceImpl implements PassiveService {
    private final OutFlowsRepository outFlowsRepository;
    private final ExpensesRepository expensesRepository;
    private final PendingPaymentRepository pendingPaymentRepository;
    private final CashClosingService cashClosingService;
    private final InvoiceSupplierRepository invoiceSupplierRepository;
    private final SupplierPaymentRepository supplierPaymentRepository;
    private final PassiveRepository passiveRepository;
    @Override
    public Passive getPassive() {
        Date date = cashClosingService.getDateCashClosing();
        Optional<Passive> passiveOptional = passiveRepository.findFirstByDateAfter(date);
        Passive passive;
        passive = passiveOptional.orElseGet(()-> {
                Passive ps = new Passive();
                ps.setDate(new Date());
                return passiveRepository.save(ps);
            }
        );

        Double totalExpenses = 0.0;
        Double totalPendingPayments = 0.0;

        if(date !=null){

            List<Expenses> expenses = expensesRepository.findByDateAfter(date);
            List<PendingPayments> pendingPayments = pendingPaymentRepository.findByDateAfter(date);
            if(!expenses.isEmpty()){
                totalExpenses = expenses.stream().mapToDouble(Expenses::getAmount).sum();

            }
            if(!pendingPayments.isEmpty()){
                totalPendingPayments = pendingPayments.stream().mapToDouble(PendingPayments::getAmount).sum();
            }
            passive.setOutFlows(totalExpenses+totalPendingPayments);


            passive.setAccount_suppliers(invoiceSupplierRepository.findByDateAfter(date).stream().mapToDouble(InvoiceSupplier::getTotal).sum());
            passive.setPending_payments(supplierPaymentRepository.findByDateAfter(date).stream().mapToDouble(SupplierPayment::getPayment).sum());
        }else{

            passive.setAccount_suppliers(invoiceSupplierRepository.findAll().stream().mapToDouble(InvoiceSupplier::getTotal).sum());
            passive.setPending_payments(supplierPaymentRepository.findAll().stream().mapToDouble(SupplierPayment::getPayment).sum());
        }
        passive.setTotal(passive.getOutFlows()+passive.getAccount_suppliers()+passive.getPending_payments());
        return passive;
    }
}
