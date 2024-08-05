package com.servidor.gestiondeventas.services.receipts.AFIP.tools;


import lombok.Data;

@Data
public class PostHeader {
    private String url;
    private String contentType;
    private String soapAction;
    private String xml;

}
