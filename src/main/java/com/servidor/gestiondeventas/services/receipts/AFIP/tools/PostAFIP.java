package com.servidor.gestiondeventas.services.receipts.AFIP.tools;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PostAFIP {
    public String PostXML(PostHeader postHeader) throws IOException {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(postHeader.getUrl());
            post.setHeader("Content-Type",postHeader.getContentType());
            post.setHeader("SOAPAction", postHeader.getSoapAction());

            StringEntity entity = new StringEntity(postHeader.getXml());
            post.setEntity(entity);

            try(CloseableHttpResponse response = client.execute(post)){
                HttpEntity reponseEntity = response.getEntity();
                return EntityUtils.toString(reponseEntity);
            }
        }catch (Exception e){
            throw new IOException("Error al consultar la url "+postHeader.getUrl(), e);

        }

    };

}
