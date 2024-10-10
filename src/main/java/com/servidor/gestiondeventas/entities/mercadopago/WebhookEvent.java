package com.servidor.gestiondeventas.entities.mercadopago;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.servidor.gestiondeventas.entities.mercadopago.tools.WebhooksDataConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asegúrate de usar la estrategia de generación de ID adecuada
    private Long id;

    @Column(name = "action")
    private String action;

    @Column(name = "api_version")
    private String apiVersion;

    @Column(name = "data")
    @Convert(converter = WebhooksDataConverter.class)
    private WebhooksData data;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "live_mode")
    private boolean liveMode;

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    private String userId;
}
