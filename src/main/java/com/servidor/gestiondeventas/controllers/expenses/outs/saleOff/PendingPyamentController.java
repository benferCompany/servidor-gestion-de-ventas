package com.servidor.gestiondeventas.controllers.expenses.outs.saleOff;

import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.PendingPayments;
import com.servidor.gestiondeventas.entities.expenses.outs.saleOff.pendingPayments.dto.PendingPaymentsDTO;
import com.servidor.gestiondeventas.services.expenses.outs.saleOff.PendingPaymentsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("pendingPayments")
@AllArgsConstructor
@CrossOrigin(origins ="*")
public class PendingPyamentController {
    private final PendingPaymentsService pendingPaymentsService;
    @GetMapping
    public ResponseEntity<List<PendingPaymentsDTO>> getPendingPayments(){
        return new ResponseEntity<>(pendingPaymentsService.getPendingPayments(), HttpStatus.OK) ;
    }

    @GetMapping("/search")
    public List<PendingPaymentsDTO> searchByDescriptionAndDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam("description") String description) {

        return null;
    }
    @PostMapping
    public ResponseEntity<PendingPaymentsDTO> createPendingMayments(@RequestBody  PendingPayments pendingPayments){

        return new ResponseEntity<>(pendingPaymentsService.createPendingPayments(pendingPayments),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public boolean deletePendingPayments(@PathVariable Long id){
        return pendingPaymentsService.deletaPendingPayments(id);
    }

    @PutMapping
    public ResponseEntity<PendingPaymentsDTO> updatePendingPayments(@RequestBody PendingPayments pendingPayments){
        return new ResponseEntity<>(pendingPaymentsService.updatePendingPayments(pendingPayments),HttpStatus.OK);
    }
}
