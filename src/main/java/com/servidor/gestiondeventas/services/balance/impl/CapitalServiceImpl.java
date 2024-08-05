package com.servidor.gestiondeventas.services.balance.impl;

import com.servidor.gestiondeventas.entities.balance.Capital;
import com.servidor.gestiondeventas.repository.balance.CapitalRepository;
import com.servidor.gestiondeventas.services.balance.CapitalService;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CapitalServiceImpl implements CapitalService {
    private final CapitalRepository capitalRepository;
    private final CashClosingService cashClosingService;
    @Override
    @Transactional
    public Capital getCapitalByData() {
        Optional<Capital> capital = capitalRepository.findFirstByDateAfter(cashClosingService.getDateCashClosing());
        if(capital.isPresent()){
            return capital.get();
        }else{
            Capital capitalEmpty = new Capital();
            capitalEmpty.setCapital_social(0.0);
            capitalEmpty.setTotal(0.0);
            capitalEmpty.setThe_result(0.0);
            capitalEmpty.setProfit(0.0);
            capitalEmpty.setDate(new Date());
            return capitalRepository.save(capitalEmpty);
        }

    }

    @Override
    public void capitalSumStock(Double total) {
        Optional<Capital> capital = capitalRepository.findTopByOrderByDateDesc();
        if(capital.isPresent()){
            Double totalPrice = capital.get().getCapital_social() + total;
            capital.get().setCapital_social(totalPrice);
            capitalRepository.save(capital.get());
        }
    }
}
