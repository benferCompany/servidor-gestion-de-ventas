package com.servidor.gestiondeventas.services.mercadopago;

import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;

public interface WebhookEventService {
    public WebhookEvent createWebhookEvent(WebhookEvent webhookEvent);
}
