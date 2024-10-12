package com.servidor.gestiondeventas.services.mercadopago;



import java.util.List;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;
import com.servidor.gestiondeventas.entities.receipts.Details;

public interface MercadoPagoService {
    public Preference createPayment(Details details) throws MPException, MPApiException;
    public Payment getPaymentById(Long id)throws MPException, MPApiException;
    public List<Payment> getPayment(List<WebhookEvent> webhooks)throws MPException, MPApiException;
}
