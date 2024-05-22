package com.servidor.gestiondeventas.services.receipts.AFIP.impl;

import com.servidor.gestiondeventas.entities.receipts.AFIP.AuthAFIP;
import com.servidor.gestiondeventas.repository.receipts.AFIP.AuthAFIPRepository;
import com.servidor.gestiondeventas.services.receipts.AFIP.AuthAFIPService;
import com.servidor.gestiondeventas.services.receipts.AFIP.tools.ToolsAFIP;
import com.servidor.gestiondeventas.tools.AFIP.generateTA.AfipLoginTicketGenerator;
import com.servidor.gestiondeventas.tools.MessageBoolean;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthAFIPServiceImpl implements AuthAFIPService {
    private final AfipLoginTicketGenerator afipLoginTicketGenerator;
    private final ToolsAFIP toolsAFIP;
    private final AuthAFIPRepository authAFIPRepository;

    //Solicita el CAE.
    @Override
    public MessageBoolean sendSoapRequest() throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String url;
            if(false){
                url = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
            }else{
                url  = "https://wsaahomo.afip.gov.ar/ws/services/LoginCms";
            }
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "text/xml;charset=UTF-8");
            post.setHeader("SOAPAction", "");

            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsaa=\"http://wsaa.view.sua.dvadac.desein.afip.gov\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<wsaa:loginCms>" +
                    "<wsaa:in0>" + afipLoginTicketGenerator.generateLoginTicketRequest() + "</wsaa:in0>" +
                    "</wsaa:loginCms>" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";

            StringEntity entity = new StringEntity(xml);
            post.setEntity(entity);

            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity responseEntity = response.getEntity();
                return toolsAFIP.validateResult(EntityUtils.toString(responseEntity));


            }
        } catch (Exception e) {
            // Consider a more specific handling of various exceptions that can occur
            throw new IOException("Error sending SOAP request to AFIP", e);
        }
    }

    @Override
    public AuthAFIP getAuthByCuit(String cuit) {
        return authAFIPRepository.findFirstByCuit(cuit);
    }

    @Override
    public AuthAFIP createAuth(AuthAFIP authAFIP) {
        AuthAFIP authAFIP1 = getAuthByCuit(authAFIP.getCuit());
        if(authAFIP1 == null){
            return authAFIPRepository.save(authAFIP);
        }else{
            return null;
        }

    }
    @Override
    @Transactional
    public MessageBoolean createAuthOrEdit(AuthAFIP authAFIP) {
        AuthAFIP authAFIP1 = getAuthByCuit(authAFIP.getCuit());

        if(authAFIP1 == null){
            return new MessageBoolean(createAuth(authAFIP).toString(),"Se creó con éxito", true);


        }else{
            return new MessageBoolean(editAuth(authAFIP).toString(),"Se actializó con éxito", true);
        }

    }

    @Override
    public AuthAFIP editAuth(AuthAFIP authAFIP) {
        AuthAFIP authAFIP1 = getAuthByCuit(authAFIP.getCuit());

        if(authAFIP1.getCuit().equals(authAFIP.getCuit())){
            authAFIP1.setToken(authAFIP.getToken());
            authAFIP1.setSign(authAFIP.getSign());
            return authAFIPRepository.save(authAFIP1);
        }
        return null;
    }

    @Override
    public String deleteAuth(Long id) {
        return null;
    }

    @Override
    public List<AuthAFIP> getAuths(List<AuthAFIP> authAFIPS) {
        return null;
    }

}
