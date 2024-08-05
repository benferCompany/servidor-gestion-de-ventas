package com.servidor.gestiondeventas.services.receipts.AFIP.FEComp;

import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostHeader;

import java.io.IOException;

public interface UltimoComprobanteService {
    public String getUltimoComprobante(PostHeader postHeader) throws IOException;
}
