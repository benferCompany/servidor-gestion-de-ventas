package com.servidor.gestiondeventas.services.receipts.AFIP.tools.SolicitarGet;

import com.servidor.gestiondeventas.services.receipts.AFIP.tools.PostHeader;
import lombok.Data;

@Data
public class FECAEDetRequest {
    private PostHeader postHeader;
    private int concepto;
    private int docTipo;
    private long docNro;
    private int cbteDesde;
    private int cbteHasta;
    private String cbteFch;
    private double impTotal;
    private double impTotConc;
    private double impNeto;
    private double impOpEx;
    private double impTrib;
    private double impIVA;
    private String monId;
    private double monCotiz;
}
