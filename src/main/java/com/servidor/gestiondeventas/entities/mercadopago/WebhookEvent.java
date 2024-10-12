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
    private String id;

    @Column(name = "action")
    private String action;

    @Column(name = "api_version")
    private String api_version;

    @Column(name = "data")
    @Convert(converter = WebhooksDataConverter.class)
    private WebhooksData data;

    @Column(name = "date_created")
    private LocalDateTime date_created;

    @Column(name = "live_mode")
    private boolean live_mode;

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    private String user_id;
    @Column(name="email")
    private String email;
}
