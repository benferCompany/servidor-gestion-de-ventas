package com.servidor.gestiondeventas.services.mercadopago;

import java.util.List;

import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;

public interface WebhookEventService {
    public WebhookEvent createWebhookEvent(WebhookEvent webhookEvent);
    public List<WebhookEvent> getWebHooks();
    public WebhookEvent updateWebHook(WebhookEvent webhookEvent);
    public boolean deleteWebHookEvent(Long idWebHook); 
    public WebhookEvent getWebhookById(Long webHookId);
    public List<WebhookEvent> getWebhooksByEmail(String email);
}
