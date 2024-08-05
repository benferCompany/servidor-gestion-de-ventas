package com.servidor.gestiondeventas.services.receipts.AFIP.tools;

import com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.FECAEDetRequest;
import com.servidor.gestiondeventas.tools.MessageBoolean;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ToolsAFIP {

    public MessageBoolean validateResult(String xmlResponse){

        try {

            JSONObject jsonObject = XML.toJSONObject(xmlResponse);

            // Acceder a los datos dentro de faultstring
            JSONObject body = jsonObject.getJSONObject("soapenv:Envelope")
                    .getJSONObject("soapenv:Body");


            // Verificar si hay un fallo
            if (body.has("soapenv:Fault")) {
                JSONObject fault = body.getJSONObject("soapenv:Fault");
                // Manejar el caso donde "faultstring" no es un objeto JSON
                if (fault.get("faultstring") instanceof String) {
                    String faultString = fault.getString("faultstring");
                    return new MessageBoolean(faultString,"",false);
                }
            }else {
                JSONObject loginCmsResponse = body.getJSONObject("loginCmsResponse");
                if (loginCmsResponse.has("loginCmsReturn")) {
                    JSONObject jsonObjectResponse = XML.toJSONObject(loginCmsResponse.get("loginCmsReturn").toString());
                    return new MessageBoolean(jsonObjectResponse.toString(),"",true);
                }
            }
        } catch (Exception e) {
            // Manejar la excepción adecuadamente, puede ser un problema de conversión de XML a JSON o un error en la solicitud SOAP
            e.printStackTrace();
            return new MessageBoolean(e.toString(), "", false);
        }
        return null;
    }

    public JSONObject jsonResult(String xmlResponse){
        JSONObject jsonObject = XML.toJSONObject(xmlResponse);
        return jsonObject.getJSONObject("soap:Envelope").getJSONObject("soap:Body");
    }

    public String dateAfip (){
        LocalDate fechaActual = LocalDate.now();

        // Define el formato deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Formatea la fecha actual
        return fechaActual.format(formato);
    }

    public String convertXML(FECAEDetRequest fecaeDetRequest){

        return  "<ar:FECAEDetRequest>" +
                "                \"                  <ar:Concepto>"+fecaeDetRequest.getConcepto()+"</ar:Concepto>\\n\" +\n" +
                "                \"                  <ar:DocTipo>"+fecaeDetRequest.getDocTipo()+"</ar:DocTipo>\\n\" +\n" +
                "                \"                  <ar:DocNro>"+fecaeDetRequest.getDocNro()+"</ar:DocNro>\\n\" +\n" +
                "                \"                  <ar:CbteDesde>"+fecaeDetRequest.getCbteDesde()+"</ar:CbteDesde>\\n\" +\n" +
                "                \"                  <ar:CbteHasta>"+fecaeDetRequest.getCbteHasta()+"</ar:CbteHasta>\\n\" +\n" +
                "                \"                  <ar:CbteFch>"+fecaeDetRequest.getCbteFch()+"</ar:CbteFch>\\n\" +\n" +
                "                \"                  <ar:ImpTotal>"+fecaeDetRequest.getImpTotal()+"</ar:ImpTotal>\\n\" +\n" +
                "                \"                  <ar:ImpTotConc>"+fecaeDetRequest.getImpTotConc()+"</ar:ImpTotConc>\\n\" +\n" +
                "                \"                  <ar:ImpNeto>"+fecaeDetRequest.getImpNeto()+"</ar:ImpNeto>\\n\" +\n" +
                "                \"                  <ar:ImpOpEx>"+fecaeDetRequest.getImpOpEx()+"</ar:ImpOpEx>\\n\" +\n" +
                "                \"                  <ar:ImpTrib>"+fecaeDetRequest.getImpTrib()+"</ar:ImpTrib>\\n\" +\n" +
                "                \"                  <ar:ImpIVA>"+fecaeDetRequest.getImpIVA()+"</ar:ImpIVA>\\n\" +\n" +
                "                \"                  <ar:MonId>"+fecaeDetRequest.getMonId()+"</ar:MonId>\\n\" +\n" +
                "                \"                  <ar:MonCotiz>"+fecaeDetRequest.getMonCotiz()+"</ar:MonCotiz>\\n\" +\n" +
                "                \"                  \\n\" +\n" +
                "                \"               </ar:FECAEDetRequest>";

    }

}
