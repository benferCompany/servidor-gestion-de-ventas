package com.servidor.gestiondeventas.repository.mercadopago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servidor.gestiondeventas.entities.mercadopago.WebhookEvent;

@Repository
public interface WebhookEventRepository extends JpaRepository<WebhookEvent,Long> {
    
}
