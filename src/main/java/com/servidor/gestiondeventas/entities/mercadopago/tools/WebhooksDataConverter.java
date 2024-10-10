package com.servidor.gestiondeventas.entities.mercadopago.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servidor.gestiondeventas.entities.mercadopago.WebhooksData;


@Converter(autoApply = true)
public class WebhooksDataConverter implements AttributeConverter<WebhooksData, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(WebhooksData webhooksData) {
        try {
            return objectMapper.writeValueAsString(webhooksData);
        } catch (Exception e) {
            throw new RuntimeException("Error converting WebhooksData to JSON", e);
        }
    }

    @Override
    public WebhooksData convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, WebhooksData.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to WebhooksData", e);
        }
    }
}
