package com.servidor.gestiondeventas.controllers.receipts.AFIP.FECAE;

import com.servidor.gestiondeventas.services.receipts.AFIP.FECAE.SolicitarService;

import com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.FECAEDetRequest;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.ToolsAFIP;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/getCAE")
@CrossOrigin(value = "*")
public class SolicitarController {
    private final SolicitarService solicitarService;
    private final ToolsAFIP toolsAFIP;

    @PostMapping("/string")
    public ResponseEntity<String> getCAEString(@RequestBody FECAEDetRequest fecaeDetRequest) throws IOException {

        return new ResponseEntity<>(toolsAFIP.jsonResult(solicitarService.getCAE(fecaeDetRequest)).toString(), HttpStatus.OK);


    }
}
