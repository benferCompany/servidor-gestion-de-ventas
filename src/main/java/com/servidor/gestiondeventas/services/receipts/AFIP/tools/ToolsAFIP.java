package com.servidor.gestiondeventas.services.receipts.AFIP.tools;

import com.servidor.gestiondeventas.tools.MessageBoolean;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Component
public class ToolsAFIP {

    public MessageBoolean validateResult(String xmlResponse){

        try {

            // Convertir XML a JSON
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
}
