package com.servidor.gestiondeventas.tools.mercadopago;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMercadoPago {
    @Bean
    public void MercadoPago(){
        MercadoPagoConfig.setAccessToken("APP_USR-2089742873293792-072314-a4302a96343bb453bfb47078f8ff03a1-477455682");
    }
}
