package com.servidor.gestiondeventas.tools.mercadopago;


import lombok.Data;

import java.math.BigDecimal;


@Data

public class MercadoRequest {
    private String token;
    private String issuer_id;
    private String payment_method_id;
    private BigDecimal transaction_amount;
    private int installments;
    private String email;
    private String type;
    private String number;
}
