package com.servidor.gestiondeventas.services.persons;

import com.servidor.gestiondeventas.entities.persons.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    public List<Supplier> getSupplier();

    public Optional<Supplier> getSupplierById(Long idSupplier);

    public Supplier createSupplier(Supplier supplier);

    public Supplier editSupplier(Supplier supplier);

    public boolean deleteSupplier(Long idSupplier);
}
