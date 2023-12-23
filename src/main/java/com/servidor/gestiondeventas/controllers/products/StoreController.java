package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.Store;
import com.servidor.gestiondeventas.entities.products.dto.StoreDTO;
import com.servidor.gestiondeventas.services.products.StoreService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<StoreDTO>> getStore(){
        return new ResponseEntity<>(storeService.getStore(), HttpStatus.OK);
    }

    @GetMapping("/{idStore}")
    public ResponseEntity<Optional<Store>> getSupplierById(@PathVariable Long idStore){
        return new ResponseEntity<>(storeService.getStoreById(idStore), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store){
        return new ResponseEntity<>(storeService.createStore(store), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StoreDTO> editStore(@RequestBody Store store){
        return new ResponseEntity<>(storeService.editStore(store),HttpStatus.OK);
    }
    @DeleteMapping
    public String deleteStore(@PathVariable Long idStore){
        boolean booleanStore = storeService.deleteStore(idStore);
        if(booleanStore){
            return "Se elimino el Almacen con éxito";
        }
        return "El id de este Almacen no existe";
    }
}
