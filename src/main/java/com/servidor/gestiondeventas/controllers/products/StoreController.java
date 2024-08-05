package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.Product;
import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.dto.ProductDTO;
import com.servidor.gestiondeventas.entities.products.dto.StoreDTO;
import com.servidor.gestiondeventas.services.products.StoreService;
import com.servidor.gestiondeventas.services.products.tools.ItemSearchResult;
import com.servidor.gestiondeventas.tools.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/stores")
@Data
@AllArgsConstructor
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getStore() {
        return new ResponseEntity<>(storeService.getStore(), HttpStatus.OK);
    }

    @GetMapping("/{idStore}")
    public ResponseEntity<Optional<Store>> getSupplierById(@PathVariable Long idStore) {
        return new ResponseEntity<>(storeService.getStoreById(idStore), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        return new ResponseEntity<>(storeService.createStore(store), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StoreDTO> editStore(@RequestBody Store store) {
        return new ResponseEntity<>(storeService.editStore(store), HttpStatus.OK);
    }

    @DeleteMapping("/{idStore}")
    public String deleteStore(@PathVariable Long idStore) {
        boolean booleanStore = storeService.deleteStore(idStore);
        if (booleanStore) {
            return "Se elimino el Almacen con éxito";
        }
        return "El id de este Almacen no existe";
    }

    @PostMapping("/name")
    public ResponseEntity<Page<StoreDTO>> getProductByName(
            @RequestBody Store store,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ItemSearchResult itemSearchResult = storeService.getStoreByName(store.getProduct().getDescription(), page, size);

        List<StoreDTO> storeDTOList = itemSearchResult.getResultList();
        Long totalElements = itemSearchResult.getTotalElements();

        return new ResponseEntity<>(new PageImpl<>(storeDTOList, PageRequest.of(page, size), totalElements), HttpStatus.OK);
    }
    @PostMapping("/createOrUpdate")
    public ResponseEntity<Message<StoreDTO>> createOrUpdate(@RequestBody Store store){
        return new ResponseEntity<>(storeService.createOrUpdate(store),HttpStatus.OK);
    }

    @GetMapping("/valueStore")
    public Double getValueStore(){
        if(storeService.getValueStore()!=null){
            return storeService.getValueStore();
        }
        return 0.0;

    }
}
