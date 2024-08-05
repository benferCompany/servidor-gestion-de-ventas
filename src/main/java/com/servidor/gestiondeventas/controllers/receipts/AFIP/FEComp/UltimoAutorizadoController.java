package com.servidor.gestiondeventas.controllers.receipts.AFIP.FEComp;

import com.servidor.gestiondeventas.services.receipts.AFIP.FEComp.UltimoComprobanteService;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostHeader;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.ToolsAFIP;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/comprobante")
@CrossOrigin(value = "*")
@AllArgsConstructor
public class UltimoAutorizadoController {
    final private UltimoComprobanteService ultimoComprobanteService;
    final private ToolsAFIP toolsAFIP;
    @GetMapping(value = "/getLastCom")
    public String getUltimoComprobante() throws IOException {
        PostHeader postHeader = new PostHeader();
        postHeader.setUrl("https://wswhomo.afip.gov.ar/wsfev1/service.asmx");
        postHeader.setContentType("text/xml;charset=UTF-8");
        postHeader.setSoapAction("http://ar.gov.afip.dif.FEV1/FECompUltimoAutorizado");

        String xmlResponse =ultimoComprobanteService.getUltimoComprobante(postHeader);

        return toolsAFIP.jsonResult(xmlResponse).toString();

    }
}
