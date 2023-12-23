package com.servidor.gestiondeventas.services.persons;

import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.SalesPerson;

import java.util.List;
import java.util.Optional;

public interface SalesPersonService {
    public List<SalesPerson> getSalesPerson();

    public Optional<SalesPerson> getSalesPersonById(Long idSalesPerson);

    public SalesPerson createSalesPerson(SalesPerson salesPerson);

    public SalesPerson editSalesPerson(SalesPerson salesPerson);

    public boolean deleteSalesPerson(Long idSalesPerson);
}
