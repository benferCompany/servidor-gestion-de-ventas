package com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.dto;

import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostHeader;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.FECAEDetRequest;
import lombok.Data;

@Data
public class FECAEDetRequestDTO {

    public static String convertXML(FECAEDetRequest fecaeDetRequest){

        return
                "<ar:FECAEDetRequest>"+
                "                                  <ar:Concepto>"+fecaeDetRequest.getConcepto()+"</ar:Concepto>\n" +
                "                                  <ar:DocTipo>"+fecaeDetRequest.getDocTipo()+"</ar:DocTipo>\n" +
                "                                  <ar:DocNro>"+fecaeDetRequest.getDocNro()+"</ar:DocNro>\n" +
                "                                  <ar:CbteDesde>"+fecaeDetRequest.getCbteDesde()+"</ar:CbteDesde>+\n" +
                "                                  <ar:CbteHasta>"+fecaeDetRequest.getCbteHasta()+"</ar:CbteHasta> +\n" +
                "                                  <ar:CbteFch>"+fecaeDetRequest.getCbteFch()+"</ar:CbteFch>\n" +
                "                                  <ar:ImpTotal>"+fecaeDetRequest.getImpTotal()+"</ar:ImpTotal>\n" +
                "                                  <ar:ImpTotConc>"+fecaeDetRequest.getImpTotConc()+"</ar:ImpTotConc>\n" +
                "                                  <ar:ImpNeto>"+fecaeDetRequest.getImpNeto()+"</ar:ImpNeto>\n" +
                "                                  <ar:ImpOpEx>"+fecaeDetRequest.getImpOpEx()+"</ar:ImpOpEx>\n" +
                "                                  <ar:ImpTrib>"+fecaeDetRequest.getImpTrib()+"</ar:ImpTrib>\n" +
                "                                 <ar:ImpIVA>"+fecaeDetRequest.getImpIVA()+"</ar:ImpIVA>\n" +
                "                                  <ar:MonId>"+fecaeDetRequest.getMonId()+"</ar:MonId>\n" +
                "                                  <ar:MonCotiz>"+fecaeDetRequest.getMonCotiz()+"</ar:MonCotiz>\n" +
                "</ar:FECAEDetRequest>";

    }
}
