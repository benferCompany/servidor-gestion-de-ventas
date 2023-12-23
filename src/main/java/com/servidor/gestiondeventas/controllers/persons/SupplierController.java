package com.servidor.gestiondeventas.controllers.persons;

import com.servidor.gestiondeventas.entities.persons.Supplier;
import com.servidor.gestiondeventas.entities.persons.dto.SupplierDTO;
import com.servidor.gestiondeventas.services.persons.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> getSupplier() {
        return new ResponseEntity<>(supplierService.getSupplier(), HttpStatus.OK);
    }

    @GetMapping("/{idSupplier}")
    public ResponseEntity<Optional<Supplier>> getSupplierById(@PathVariable Long idSupplier) {
        return new ResponseEntity<>(supplierService.getSupplierById(idSupplier), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.createSupplier(supplier), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SupplierDTO> editSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(SupplierDTO.fromSupplierDTO(supplierService.editSupplier(supplier)), HttpStatus.OK);
    }

    @DeleteMapping
    public String deleteSupplier(@PathVariable Long idSupplier) {
        boolean booleanSupplier = supplierService.deleteSupplier(idSupplier);
        if (booleanSupplier) {
            return "Se elimino el vendedor con éxito";
        }
        return "El id de este vendedor no existe";
    }
}
