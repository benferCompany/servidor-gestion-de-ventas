package com.servidor.gestiondeventas.controllers.receipts;

import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.services.receipts.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    DetailsService detailsService;

    @GetMapping
    public ResponseEntity<List<Details>> getDetails(){
        return new ResponseEntity<>(detailsService.getDetails(), HttpStatus.OK);
    }

    @GetMapping("/{idDetails}")
    public ResponseEntity<Optional<Details>> getDetailsById(@PathVariable Long idDetails){
        return new ResponseEntity<>(detailsService.getDetailsById(idDetails),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Details> createDetails(@RequestBody Details details){
        return new ResponseEntity<>(detailsService.createDetails(details), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Details> editDetails(@RequestBody Details details){
        return new ResponseEntity<>(detailsService.editDetails(details),HttpStatus.OK);
    }

    @DeleteMapping("/{idDetails}")
    public String deleteDetails(@PathVariable Long idDetails){
        boolean booleanDetails = detailsService.deleteDetails(idDetails);
        if(booleanDetails){
            return "El detalle se elimino con éxito";
        }
        return "El detalle solicitado no existe";
    }
}
