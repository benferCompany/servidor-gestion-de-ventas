package com.servidor.gestiondeventas.controllers.receipts;

import com.servidor.gestiondeventas.entities.receipts.Details;
import com.servidor.gestiondeventas.entities.receipts.dto.DetailsDto;
import com.servidor.gestiondeventas.services.receipts.AFIP.FECAE.SolicitarService;
import com.servidor.gestiondeventas.services.receipts.DetailProductService;
import com.servidor.gestiondeventas.services.receipts.DetailsService;
import com.servidor.gestiondeventas.tools.AFIP.generateTA.AfipLoginTicketGenerator;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/details")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DetailsController {
    @Autowired
    DetailsService detailsService;
    DetailProductService detailProductService;
    AfipLoginTicketGenerator afipLoginTicketGenerator;

    @GetMapping
    public ResponseEntity<List<DetailsDto>> getDetails() {
        return new ResponseEntity<>(detailsService.getDetails(), HttpStatus.OK);
    }

    @GetMapping("/{idDetails}")
    public ResponseEntity<DetailsDto> getDetailsById(@PathVariable Long idDetails) {
        return new ResponseEntity<>(detailsService.getDetailsById(idDetails), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailsDto> createDetails(@RequestBody Details details) throws IOException {

        Details newDetails = detailsService.createDetails(details);

        return new ResponseEntity<>(DetailsDto.fromEntity(newDetails), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DetailsDto> editDetails(@RequestBody Details details) {
        return new ResponseEntity<>(detailsService.editDetails(details), HttpStatus.OK);
    }

    @DeleteMapping("/{idDetails}")
    public String deleteDetails(@PathVariable Long idDetails) {
        boolean booleanDetails = detailsService.deleteDetails(idDetails);
        if (booleanDetails) {
            return "El detalle se elimino con éxito";
        }
        return "El detalle solicitado no existe";
    }


}
