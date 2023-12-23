package com.servidor.gestiondeventas.services.persons.impl;

import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import com.servidor.gestiondeventas.repository.persons.SalesPersonRepository;
import com.servidor.gestiondeventas.services.persons.SalesPersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesPersonServiceImpl implements SalesPersonService {
    private SalesPersonRepository salesPersonRepository;

    @Override
    public List<SalesPerson> getSalesPerson() {
        return salesPersonRepository.findAll();
    }

    @Override
    public Optional<SalesPerson> getSalesPersonById(Long idSalesPerson) {

        return salesPersonRepository.findById(idSalesPerson);
    }

    @Override
    public SalesPerson createSalesPerson(SalesPerson SalesPerson) {

        return salesPersonRepository.save(SalesPerson);
    }

    @Override
    public SalesPerson editSalesPerson(SalesPerson salesPerson) {

        return salesPersonRepository.save(salesPerson);
    }

    @Override
    public boolean deleteSalesPerson(Long idSalesPerson) {
        Optional<SalesPerson> salesPersonOptional = salesPersonRepository.findById(idSalesPerson);

        if (salesPersonOptional.isPresent()) {
            salesPersonRepository.delete(salesPersonOptional.get());
            return true;
        }

        return false;
    }
}
