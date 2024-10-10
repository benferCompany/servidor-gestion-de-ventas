package com.servidor.gestiondeventas.services.mercadopago.impl;

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

}
