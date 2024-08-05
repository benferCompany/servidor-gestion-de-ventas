package com.servidor.gestiondeventas.services.products.impl;

import com.servidor.gestiondeventas.entities.company.Company;
import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.dto.StoreDTO;
import com.servidor.gestiondeventas.repository.company.CompanyRepository;
import com.servidor.gestiondeventas.repository.products.ProductRepository;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.EntityEditor;
import com.servidor.gestiondeventas.repository.products.StoreRepository;
import com.servidor.gestiondeventas.services.products.StoreService;
import com.servidor.gestiondeventas.tools.GenericSearchService;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor

public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final EntityEditor<Store> storeEditor;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

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

    @Override
    public ItemSearchResult getStoreByName(String text, int page, int size) {
        GenericSearchService<Store> genericSearchService = new GenericSearchService<>(entityManager, Store.class);

        Map<String, Object> searchResult = genericSearchService.getEntitiesBySearchTerms(text, new String[]{"description","idInternal"}, page, size);
        List<Store> stores = (List<Store>) searchResult.get("resultQuery");
        Long totalElements = (Long) searchResult.get("totalElements");
        List<StoreDTO> storeDTOList = stores.stream()
                .map(StoreDTO::fromEntity)
                .collect(Collectors.toList());
        return new ItemSearchResult<>(storeDTOList, totalElements);
    }

    @Override
    public Message<StoreDTO> createOrUpdate(Store store){
        Product product = productRepository.findFirstByIdInternal(store.getProduct().getIdInternal());
        StoreDTO storeDTO = null;
        String message = "Verifique si el id de company o el idInternal de product estan cargados previamente";
        String status = "FALSE";
        if(product !=null){
            if(product.getStores().isEmpty()){
                Company company = companyRepository.getById(store.getCompany().getId());
                Store store1 = new Store();
                store1.setCompany(company);
                store1.setProduct(product);
                store1.setStock(store.getStock());
                store1.setStock_max(store.getStock_max());
                store1.setStock_min(store.getStock_min());
                store1.setLast_modication(store.getLast_modication());
                storeDTO =StoreDTO.fromEntity(storeRepository.save(store1));

                message = "La tienda se creo con éxito";
                status = "CREATED";


            }else{
                for (Store st : product.getStores()){
                    if(st.getCompany().getId().equals(store.getCompany().getId())){
                        st.setStock(store.getStock());
                        st.setStock_max(store.getStock_max());
                        st.setStock_min(store.getStock_min());
                        st.setLast_modication(store.getLast_modication());
                        storeDTO =StoreDTO.fromEntity(storeRepository.save(st));
                        message = "La tienda se actualizo con éxito";
                        status = "UPDATE";

                    }
                }
            }
        }
        return new Message<>(storeDTO, message, status);
    }

    @Override
    public Double getValueStore() {
        return storeRepository.findByCompanyId(1L).stream().mapToDouble(store->{
            return store.getStock() * store.getProduct().getCost_price();
        }).sum();

    }
}
