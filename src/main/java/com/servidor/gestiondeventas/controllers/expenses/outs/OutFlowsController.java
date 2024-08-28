package com.servidor.gestiondeventas.controllers.expenses.outs;

import com.servidor.gestiondeventas.entities.expenses.outs.outflows.OutFlows;
import com.servidor.gestiondeventas.entities.expenses.outs.outflows.dto.OutFlowsDTO;
import com.servidor.gestiondeventas.services.expenses.outs.OutFlowsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outflows")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class OutFlowsController {
    private final OutFlowsService outFlowsService;
    @GetMapping
    public ResponseEntity<OutFlows> getOutFlows(){
        return new ResponseEntity<>(outFlowsService.getOutFlows(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<OutFlowsDTO> createOutFlows(@RequestBody OutFlows outFlows){
        return new ResponseEntity<>(outFlowsService.createOutFlows(outFlows),HttpStatus.CREATED);
    }
}
