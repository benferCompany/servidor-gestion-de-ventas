package com.servidor.gestiondeventas.services.persons.impl;

import com.servidor.gestiondeventas.entities.persons.Customer;
import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.persons.dto.CustomerDTO;
import com.servidor.gestiondeventas.entities.persons.dto.SupplierDTO;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.services.persons.SupplierService;

import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.GenericSearchService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;
    final private EntityManager entityManager;
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

    @Override
    public ItemSearchResult getSupplierByName(String text, int page, int size) {
        GenericSearchService<Supplier> genericSearchService = new GenericSearchService<>(entityManager, Supplier.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text, new String[]{"name","last_name", "name_bussiness"}, page, size);
        List<Supplier> suppliers = (List<Supplier>) searchResult.get("resultQuery");
        Long totalElements = (Long) searchResult.get("totalElements");
        List<SupplierDTO> productDTOList = suppliers.stream()
                .map(SupplierDTO::fromSupplierDTO)
                .collect(Collectors.toList());
        return new ItemSearchResult<>(productDTOList, totalElements);

    }
}
