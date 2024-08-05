package com.servidor.gestiondeventas.services.receipts.AFIP.FECAE;

import com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet.FECAEDetRequest;

import java.io.IOException;

public interface SolicitarService {
    public String getCAE(FECAEDetRequest fecaeDetRequest) throws IOException;

}
