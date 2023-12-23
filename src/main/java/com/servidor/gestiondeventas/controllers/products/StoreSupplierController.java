package com.servidor.gestiondeventas.controllers.products;

import com.servidor.gestiondeventas.entities.products.StoreSupplier;
import com.servidor.gestiondeventas.entities.products.dto.StoreSupplierDTO;
import com.servidor.gestiondeventas.services.products.StoreSupplierService;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("storeSuppliers")
@Data
public class StoreSupplierController {
    @Autowired
    StoreSupplierService storeSupplierService;
    @GetMapping
    public ResponseEntity<List<StoreSupplierDTO>> getStoreSupplier(){
        return new ResponseEntity<>(storeSupplierService.getStoreSupplier(), HttpStatus.OK);
    }

    @GetMapping("/{idStoreSupplier}")
    public ResponseEntity<Optional<StoreSupplier>> getSupplierById(@PathVariable Long idStoreSupplier){
        return new ResponseEntity<>(storeSupplierService.getStoreSupplierById(idStoreSupplier), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StoreSupplier> createProduct(@RequestBody StoreSupplier storeSupplier){
        return new ResponseEntity<>(storeSupplierService.createStoreSupplier(storeSupplier), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StoreSupplierDTO> editProduct(@RequestBody StoreSupplier storeSupplier){
        return new ResponseEntity<>(StoreSupplierDTO.fromStoreSupplierDTO(storeSupplierService.editStoreSupplier(storeSupplier)),HttpStatus.OK);
    }
    @DeleteMapping
    public String deleteProduct(@PathVariable Long idStoreSupplier){
        boolean booleanProduct = storeSupplierService.deleteStoreSupplier(idStoreSupplier);
        if(booleanProduct){
            return "Se elimino el vendedor con éxito";
        }
        return "El id de este vendedor no existe";
    }
}
