package com.servidor.gestiondeventas.services.receipts.AFIP.FEComp.impl;

import com.servidor.gestiondeventas.entities.receipts.AFIP.AuthAFIP;
import com.servidor.gestiondeventas.services.receipts.AFIP.AuthAFIPService;
import com.servidor.gestiondeventas.services.receipts.AFIP.FEComp.UltimoComprobanteService;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostAFIP;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostHeader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UltimoComprobanteServiceImpl implements UltimoComprobanteService {
    final private PostAFIP postAFIP;
    final private AuthAFIPService authAFIPService;
    @Override
    public String getUltimoComprobante(PostHeader postHeader) throws IOException {
        AuthAFIP authAFIP = authAFIPService.getAuthByCuit("27350308534");
        if(authAFIP !=null){
            String fullXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ar=\"http://ar.gov.afip.dif.FEV1/\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <ar:FECompUltimoAutorizado>\n" +
                    "         <ar:Auth>\n" +
                    "            <ar:Token>"+authAFIP.getToken()+"</ar:Token>\n" +
                    "            <ar:Sign>"+authAFIP.getSign()+"</ar:Sign>\n" +
                    "            <ar:Cuit>"+authAFIP.getCuit()+"</ar:Cuit>\n" +
                    "\n" +
                    "         </ar:Auth>\n" +
                    "         <ar:PtoVta>1</ar:PtoVta>\n" +
                    "         <ar:CbteTipo>11</ar:CbteTipo>\n" +
                    "      </ar:FECompUltimoAutorizado>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            postHeader.setXml(fullXml);
            return postAFIP.PostXML(postHeader);

        }
    return "Algo salio mal, lo siento";
    }
}
