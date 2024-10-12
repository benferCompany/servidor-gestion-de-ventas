package com.servidor.gestiondeventas.entities.mercadopago;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity // Si WebhooksData necesita ser una entidad
@Data
public class WebhooksData {
    @Id
    private Long id; // Cambiado a String para coincidir con el JSON
}
