package com.servidor.gestiondeventas.tools.mercadopago;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMercadoPago {
    @Bean
    public void MercadoPago(){
        MercadoPagoConfig.setAccessToken("APP_USR-1750996509852243-082621-b926f46163f02a45ecdde9f3827427b7-1962400103");
    }
}
