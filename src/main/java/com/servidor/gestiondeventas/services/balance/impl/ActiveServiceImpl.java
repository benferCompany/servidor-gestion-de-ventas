package com.servidor.gestiondeventas.services.balance.impl;

import com.servidor.gestiondeventas.entities.balance.Active;
import com.servidor.gestiondeventas.entities.balance.Movements;
import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.repository.balance.ActiveRepository;
import com.servidor.gestiondeventas.repository.receipts.DetailsRepository;
import com.servidor.gestiondeventas.services.balance.ActiveService;
import com.servidor.gestiondeventas.services.balance.MovementsService;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import com.servidor.gestiondeventas.services.expenses.invoice.InvoiceSupplierService;
import com.servidor.gestiondeventas.services.products.StoreService;
import com.servidor.gestiondeventas.services.receipts.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.xml.soap.Detail;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActiveServiceImpl implements ActiveService {
    private final StoreService storeService;
    private final InvoiceSupplierService invoiceSupplierService;
    private final ActiveRepository activeRepository;
    private final CashClosingService cashClosingService;
    private final DetailsService detailsService;
    private final DetailsRepository detailsRepository;
    @Override
    public Active getActiveByDate() {
        Optional<Active> active = activeRepository.findFirstByDateAfter(cashClosingService.getDateCashClosing());
        if(active.isPresent()){
            if(storeService.getValueStore()!=null){
                active.get().setValue_store(storeService.getValueStore());

            }
            List<Details> details = detailsService.getDetailsDateAfter();

            if(details!=null){
                double sum = details.stream()
                        .filter(dts -> "Cuenta Corriente".equals(dts.getPaymentType()))
                        .mapToDouble(Details::getTotal)
                        .sum();
                active.get().setCurrent_account(sum);

            }


            if(active.get().getValue_store() !=null && active.get().getCurrent_account()!=null){
                active.get().setTotal(active.get().getValue_store()+active.get().getCurrent_account());

            }
        }else{
            List<Details> details = detailsRepository.findAll();

            Double cash = 0.0;
            Double current_account = 0.0;
            Double account_bank = 0.0;
            Double value_store = storeService.getValueStore();

            Active activeEmpy = new Active();
            activeEmpy.setCash(cash);
            activeEmpy.setDate(new Date());
            activeEmpy.setTotal(cash+current_account+account_bank+value_store);
            activeEmpy.setCurrent_account(current_account);
            activeEmpy.setValue_store(value_store);
            activeEmpy.setAccount_bank(account_bank);
            return activeRepository.save(activeEmpy);
        }

        return active.orElse(null);
    }
    @Override
    public Active getActive(){
        return activeRepository.findFirstBy().orElse(null);
    }

}
