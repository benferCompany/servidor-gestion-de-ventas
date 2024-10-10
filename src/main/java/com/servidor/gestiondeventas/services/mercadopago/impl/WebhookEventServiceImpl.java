package com.servidor.gestiondeventas.services.mercadopago.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;
import com.servidor.gestiondeventas.repository.mercadopago.WebhookEventRepository;
import com.servidor.gestiondeventas.services.mercadopago.WebhookEventService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WebhookEventServiceImpl implements WebhookEventService {
    private WebhookEventRepository webhookEventRepository;

    @Override
    public WebhookEvent createWebhookEvent(WebhookEvent webhookEvent) {
        try {
           
            // Guardar en la base de datos usando el repositorio
            return webhookEventRepository.save(webhookEvent);
        } catch (Exception e) {
            // Manejar excepciones
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<WebhookEvent> getWebHooks() {
        return webhookEventRepository.findAll();
    }

    @Override
    public WebhookEvent updateWebHook(WebhookEvent webhookEvent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWebHook'");
    }

    @Override
    public boolean deleteWebHookEvent(Long idWebHook) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteWebHookEvent'");
    }

    @Override
    public WebhookEvent getWebhookById(Long webHookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWebhookById'");
    }

}
