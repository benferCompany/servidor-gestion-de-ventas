package com.servidor.gestiondeventas.services.products;

import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.dto.StoreDTO;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;
import java.util.List;
import java.util.Optional;

public interface StoreService {
    public List<StoreDTO> getStore();

    public Optional<Store> getStoreById(Long idStore);

    public Store createStore(Store store);

    public StoreDTO editStore(Store store);

    public boolean deleteStore(Long idStore);

    public ItemSearchResult getStoreByName(String text, int page, int size);
    public Message<StoreDTO> createOrUpdate(Store store);
}
