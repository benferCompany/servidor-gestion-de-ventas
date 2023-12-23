package com.servidor.gestiondeventas.services.products;

import com.servidor.gestiondeventas.entities.products.StoreSupplier;
import com.servidor.gestiondeventas.entities.products.dto.StoreSupplierDTO;

import java.util.List;
import java.util.Optional;

public interface StoreSupplierService {
    public List<StoreSupplierDTO> getStoreSupplier();

    public Optional<StoreSupplier> getStoreSupplierById(Long idStoreSupplier);

    public StoreSupplier createStoreSupplier(StoreSupplier storeSupplier);

    public StoreSupplier editStoreSupplier(StoreSupplier storeSupplier);

    public boolean deleteStoreSupplier(Long idStoreSupplier);
}
