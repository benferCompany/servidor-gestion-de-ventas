package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.dto.StoreDTO;
import com.servidor.gestiondeventas.tools.EntityEditor;
import com.servidor.gestiondeventas.repository.products.StoreRepository;
import com.servidor.gestiondeventas.services.products.StoreService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor

public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final EntityEditor<Store> storeEditor;

    @Override
    public List<StoreDTO> getStore() {

        return storeRepository.findAll().stream().map(StoreDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Store> getStoreById(Long idStore) {

        return storeRepository.findById(idStore);
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public StoreDTO editStore(Store store) {

        Optional<Store> prdOptional = storeRepository.findById(store.getId());
        Store str = prdOptional.orElse(new Store()); // Puede que necesites inicializarlo de manera adecuada

        if (prdOptional.isPresent()) {
            Store updatedStore = storeEditor.editEntity(store, str);
            return StoreDTO.fromEntity(storeRepository.save(updatedStore));
        } else {
            // Manejar el caso en que no se encuentra la entidad original
            return null;
        }

    }

    @Override
    public boolean deleteStore(Long idStore) {
        Optional<Store> store = storeRepository.findById(idStore);
        if (store.isPresent()) {
            storeRepository.deleteById(idStore);
            return true;
        }
        return false;
    }
}
