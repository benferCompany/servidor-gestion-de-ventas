package com.servidor.gestiondeventas.services.persons.impl;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.services.persons.SupplierService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;
    @Override
    public List<Supplier> getSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(Long idSupplier) {

        return supplierRepository.findById(idSupplier);
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier editSupplier(Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    @Override
    public boolean deleteSupplier(Long idSupplier) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(idSupplier);

        if (supplierOptional.isPresent()) {
            supplierRepository.delete(supplierOptional.get());
            return true;
        }

        return false;
    }
}
