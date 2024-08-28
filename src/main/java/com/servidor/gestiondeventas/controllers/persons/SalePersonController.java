package com.servidor.gestiondeventas.controllers.persons;

import com.servidor.gestiondeventas.entities.persons.SalesPerson;
import com.servidor.gestiondeventas.services.persons.SalesPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salePerson")
@CrossOrigin(origins = "*")
public class SalePersonController {
    @Autowired
    SalesPersonService salesPersonService;

    @GetMapping
    public ResponseEntity<List<SalesPerson>> getSalesPerson() {
        return new ResponseEntity<>(salesPersonService.getSalesPerson(), HttpStatus.OK);
    }

    @GetMapping("/{idSalesPerson}")
    public ResponseEntity<Optional<SalesPerson>> getSalesPersonById(@PathVariable Long idSalesPerson) {
        return new ResponseEntity<>(salesPersonService.getSalesPersonById(idSalesPerson), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalesPerson> createSalesPerson(@RequestBody SalesPerson salesPerson) {
        return new ResponseEntity<>(salesPersonService.createSalesPerson(salesPerson), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SalesPerson> editSalesPerson(@RequestBody SalesPerson salesPerson) {
        return new ResponseEntity<>(salesPersonService.editSalesPerson(salesPerson), HttpStatus.OK);
    }

    @DeleteMapping
    public String deleteSalesPerson(@PathVariable Long idSalesPerson) {
        boolean booleanSalesPerson = salesPersonService.deleteSalesPerson(idSalesPerson);
        if (booleanSalesPerson) {
            return "Se elimino el vendedor con éxito";
        }
        return "El id de este vendedor no existe";
    }
}
