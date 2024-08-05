package com.servidor.gestiondeventas.controllers.expenses.closing;

import com.servidor.gestiondeventas.entities.expenses.closing.dto.CashClosingDTO;
import com.servidor.gestiondeventas.services.expenses.closing.CashClosingService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/closing")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CashClosingController {
    private final CashClosingService cashClosingService;
    @GetMapping
    public ResponseEntity<List<CashClosingDTO>> getCashClosing(){
        return new ResponseEntity<>(cashClosingService.getCashClosing(), HttpStatus.OK);
    }

    @GetMapping("/byDate")
    public ResponseEntity<CashClosingDTO> getCashClosingByDate(){
        return new ResponseEntity<>(cashClosingService.getCashClosingByDate(),HttpStatus.OK);

    }

}
