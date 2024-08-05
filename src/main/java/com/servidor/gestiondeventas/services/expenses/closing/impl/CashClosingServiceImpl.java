package com.servidor.gestiondeventas.services.expenses.closing.impl;

import com.servidor.gestiondeventas.entities.balance.dto.ActiveDTO;
import com.servidor.gestiondeventas.entities.balance.dto.MovementsDTO;
import com.servidor.gestiondeventas.entities.balance.dto.PassiveDTO;
import com.servidor.gestiondeventas.entities.expenses.closing.CashClosing;
import com.servidor.gestiondeventas.entities.expenses.closing.dto.CashClosingDTO;
import com.servidor.gestiondeventas.repository.expenses.closing.CashClosingRepository;
import com.servidor.gestiondeventas.services.balance.CapitalService;
import com.servidor.gestiondeventas.services.balance.MovementsService;
import com.servidor.gestiondeventas.services.balance.ActiveService;
import com.servidor.gestiondeventas.services.balance.PassiveService;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashClosingServiceImpl implements CashClosingService {
    private final CashClosingRepository cashClosingRepository;
    private final MovementsService movementsService;
    private final ActiveService activeService;
    private final PassiveService passiveService;
    private  final CapitalService capitalService;


    @Autowired
    public CashClosingServiceImpl(CashClosingRepository cashClosingRepository,
                                  @Lazy MovementsService movementsService,
                                  @Lazy ActiveService activeService,
                                  @Lazy PassiveService passiveService,
                                  @Lazy CapitalService capitalService){
        this.cashClosingRepository = cashClosingRepository;
        this.movementsService = movementsService;
        this.activeService = activeService;
        this.passiveService = passiveService;
        this.capitalService = capitalService;
    }
    @Override
    public List<CashClosingDTO> getCashClosing() {
        return cashClosingRepository.findAll().stream().map(CashClosingDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CashClosingDTO getCashClosingByDate() {

        Optional<CashClosing> cashClosingOptional = cashClosingRepository.findTopByOrderByDateCloseDesc();
        CashClosing cashClosing = cashClosingOptional.orElseGet(() -> {
            CashClosing newCashClosing = new CashClosing();
            ZonedDateTime nowInArgentina = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
            newCashClosing.setDateClose(Date.from(nowInArgentina.toInstant()));
            newCashClosing.setDateOpen(Date.from(nowInArgentina.toInstant()));
            return cashClosingRepository.save(newCashClosing);
        });


        cashClosing.setMovements(movementsService.getMovementsByData());
        cashClosing.setActive(activeService.getActiveByDate());
        cashClosing.setPassive(passiveService.getPassive());
        cashClosing.setCapital(capitalService.getCapitalByData());

        return CashClosingDTO.fromEntity(cashClosing);
    }

    @Override
    public Date getDateCashClosing() {
        Optional<CashClosing> cashClosingOptional = cashClosingRepository.findTopByOrderByDateCloseDesc();
        if(cashClosingOptional.isPresent()){
           return cashClosingOptional.get().getDateClose();
        }
        return new Date();
    }

    @PostConstruct
    public void onStartup() {
        // Código a ejecutar al inicio
        if(cashClosingRepository.findTopByOrderByDateCloseDesc().isEmpty()){
            execute();
        }

    }

    public void execute() {
        getCashClosingByDate();
    }


}
