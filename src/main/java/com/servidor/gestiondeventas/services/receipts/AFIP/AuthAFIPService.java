package com.servidor.gestiondeventas.services.receipts.AFIP;


import com.servidor.gestiondeventas.entities.receipts.AFIP.AuthAFIP;
import com.servidor.gestiondeventas.tools.MessageBoolean;

import java.io.IOException;
import java.util.List;

public interface AuthAFIPService {
    public MessageBoolean sendSoapRequest() throws IOException;

    public AuthAFIP getAuthByCuit(String cuit);
    public AuthAFIP createAuth(AuthAFIP authAFIP);
    public AuthAFIP editAuth(AuthAFIP authAFIP);
    public String deleteAuth(Long id);
    public List<AuthAFIP> getAuths(List<AuthAFIP> authAFIPS);


    public MessageBoolean createAuthOrEdit(AuthAFIP authAFIP);
}
