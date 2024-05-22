package com.servidor.gestiondeventas.controllers.receipts.AFIP;

import com.servidor.gestiondeventas.entities.receipts.AFIP.AuthAFIP;
import com.servidor.gestiondeventas.repository.receipts.AFIP.AuthAFIPRepository;
import com.servidor.gestiondeventas.services.receipts.AFIP.AuthAFIPService;
import com.servidor.gestiondeventas.tools.MessageBoolean;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthAFIPController {
    private final AuthAFIPService authAFIPService;
    private final AuthAFIPRepository authAFIPRepository;
    @Scheduled(fixedRate = 60000)
    public MessageBoolean certificateLoader() throws Exception {
        MessageBoolean messageBoolean = authAFIPService.sendSoapRequest();
        if(messageBoolean.isValidate()){
            JSONObject jsonObject = new JSONObject(messageBoolean.getResponse());

            // Accede a los valores dentro del objeto JSON
            JSONObject loginTicketResponse = jsonObject.getJSONObject("loginTicketResponse");

            //Obtener Credencial
            JSONObject credentials = loginTicketResponse.getJSONObject("credentials");
            String sign = credentials.getString("sign");
            String token = credentials.getString("token");

            //Obtener el cuit
            JSONObject header = loginTicketResponse.getJSONObject("header");
            String destination = header.getString("destination");
            String[] keyValuePairs = destination.split(", ");
            String[] keyValue = keyValuePairs[0].split("=");
            String cuit = keyValue[1].split(" ")[1].toString();
            AuthAFIP authAFIP = new AuthAFIP();
            authAFIP.setCuit(cuit);
            authAFIP.setSign(sign);
            authAFIP.setToken(token);

            return authAFIPService.createAuthOrEdit(authAFIP);

        }else {

            return messageBoolean;
        }
    }
    @GetMapping("/getAuth")
    public ResponseEntity<AuthAFIP> getAuth(){
        return new ResponseEntity<>(authAFIPService.getAuthByCuit("27350308534"), HttpStatus.OK);
    }
}
