package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.StoreSupplier;
import com.servidor.gestiondeventas.entities.products.dto.StoreDTO;
import com.servidor.gestiondeventas.entities.products.dto.StoreSupplierDTO;
import com.servidor.gestiondeventas.repository.persons.SupplierRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.repository.products.StoreSupplierRepository;
import com.servidor.gestiondeventas.services.persons.SupplierService;
import com.servidor.gestiondeventas.services.products.StoreSupplierService;
import com.servidor.gestiondeventas.tools.EntityEditor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor

public class StoreSupplierServiceImpl implements StoreSupplierService {
    private final StoreSupplierRepository storeSupplierRepository;
    private final EntityEditor<StoreSupplier> storeSupplierEditor;

    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;

    @Override
    public List<StoreSupplierDTO> getStoreSupplier() {
        List<StoreSupplierDTO> storeSupplierDTOs = new ArrayList<>();
        storeSupplierDTOs = storeSupplierRepository.findAll().stream()
                .map(StoreSupplierDTO::fromStoreSupplierDTO).collect(Collectors.toList());
        return storeSupplierDTOs;
    }

    @Override
    public Optional<StoreSupplier> getStoreSupplierById(Long idStoreSupplier) {

        return storeSupplierRepository.findById((idStoreSupplier));

    }

    @Override
    public StoreSupplier createStoreSupplier(StoreSupplier storeSupplier) {
        StoreSupplier newStoreSupplier = new StoreSupplier();

        newStoreSupplier.setIdInternal(storeSupplier.getIdInternal());
        newStoreSupplier.setIdSupplierOne(storeSupplier.getIdSupplierOne());
        newStoreSupplier.setIdSupplierTwo(storeSupplier.getIdSupplierTwo());
        newStoreSupplier.setSupplier(storeSupplier.getSupplier());
        newStoreSupplier.setProducts(storeSupplier.getProducts());

        return storeSupplierRepository.save(newStoreSupplier);

    }

    @Override
    public StoreSupplier editStoreSupplier(StoreSupplier storeSupplier) {
        Optional<StoreSupplier> optionalStoreSupplier = storeSupplierRepository.findById(storeSupplier.getId());
        if (optionalStoreSupplier.isPresent()) {
            List<Product> products = new ArrayList<Product>();
            Optional<Product> product = productRepository.findById(storeSupplier.getProducts().get(0).getId());
            if (product.isPresent()) {
                products.add(product.get());
                storeSupplier.setProducts(products);
            } else {
                return null;
            }
            supplierService.editSupplier(storeSupplier.getSupplier());

            return storeSupplierRepository.save(storeSupplier);
        }

        return null;

    }

    @Override
    public boolean deleteStoreSupplier(Long idStoreSupplier) {
        Optional<StoreSupplier> storeSupplier = storeSupplierRepository.findById((idStoreSupplier));
        if (storeSupplier.isPresent()) {
            storeSupplierRepository.deleteById(idStoreSupplier);
            return true;
        }
        return false;
    }
}
