package com.servidor.gestiondeventas.services.receipts.AFIP.FECAE.impl;

import com.servidor.gestiondeventas.entities.receipts.AFIP.AuthAFIP;
import com.servidor.gestiondeventas.services.receipts.AFIP.AuthAFIPService;
import com.servidor.gestiondeventas.services.receipts.AFIP.FECAE.SolicitarService;
import com.servidor.gestiondeventas.services.receipts.AFIP.FEComp.UltimoComprobanteService;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostAFIP;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostHeader;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.FECAEDetRequest;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.dto.FECAEDetRequestDTO;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.ToolsAFIP;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class SolicitarServiceImpl implements SolicitarService {
    private final UltimoComprobanteService ultimoComprobanteService;
    private final ToolsAFIP toolsAFIP;
    private final AuthAFIPService authAFIPService;
    private final PostAFIP postAFIP;
    @Override
    public String getCAE(FECAEDetRequest fecaeDetRequest) throws IOException {
        AuthAFIP authAFIP = authAFIPService.getAuthByCuit("27350308534");
        fecaeDetRequest.setCbteFch(toolsAFIP.dateAfip());
        //Consultar el ultimp comprobante
        PostHeader postHeader = fecaeDetRequest.getPostHeader();
        System.out.println(fecaeDetRequest.toString());
        String xmlResponse =ultimoComprobanteService.getUltimoComprobante(postHeader);
        JSONObject FECompUltimoAutorizadoResult  = toolsAFIP.jsonResult(xmlResponse)
                .getJSONObject("FECompUltimoAutorizadoResponse")
                .getJSONObject("FECompUltimoAutorizadoResult");

        String PtoVta = FECompUltimoAutorizadoResult.getNumber("PtoVta").toString();
        String CbteTipo = FECompUltimoAutorizadoResult.getNumber("CbteTipo").toString();
        int CbteNro = FECompUltimoAutorizadoResult.getNumber("CbteNro").intValue()+1;

        fecaeDetRequest.setCbteHasta(CbteNro);
        fecaeDetRequest.setCbteDesde(CbteNro);
        String xmlFactura = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ar=\"http://ar.gov.afip.dif.FEV1/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ar:FECAESolicitar>\n" +
                "         <!--Optional:-->\n" +
                "         <ar:Auth>\n" +
                "           <ar:Token>"+authAFIP.getToken()+"</ar:Token>\n" +
                "            <ar:Sign>"+authAFIP.getSign()+"</ar:Sign>\n" +
                "            <ar:Cuit>"+authAFIP.getCuit()+"</ar:Cuit>\n" +
                "         </ar:Auth>\n" +
                "         <!--Optional:-->\n" +
                "         <ar:FeCAEReq>\n" +
                "            <!--Optional:-->\n" +
                "            <ar:FeCabReq>\n" +
                "               <ar:CantReg>1</ar:CantReg>\n" +
                "               <ar:PtoVta>"+PtoVta+"</ar:PtoVta>\n" +
                "               <ar:CbteTipo>"+CbteTipo+"</ar:CbteTipo>\n" +
                "            </ar:FeCabReq>\n" +
                "            <!--Optional:-->\n" +
                "            <ar:FeDetReq>\n"
                                +FECAEDetRequestDTO.convertXML(fecaeDetRequest)+
                            "</ar:FeDetReq>\n"+
                "         </ar:FeCAEReq>\n" +
                "      </ar:FECAESolicitar>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        PostHeader postHeaderFactura = new PostHeader();
        postHeaderFactura.setUrl("https://wswhomo.afip.gov.ar/wsfev1/service.asmx");
        postHeaderFactura.setContentType("text/xml;charset=UTF-8");
        postHeaderFactura.setSoapAction("http://ar.gov.afip.dif.FEV1/FECAESolicitar");
        postHeaderFactura.setXml(xmlFactura);

        return postAFIP.PostXML(postHeaderFactura);

    }
}
