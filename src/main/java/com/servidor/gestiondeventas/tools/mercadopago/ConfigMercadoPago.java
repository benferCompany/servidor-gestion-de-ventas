package com.servidor.gestiondeventas.tools.mercadopago;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMercadoPago {
    @Bean
    public void MercadoPago(){
        MercadoPagoConfig.setAccessToken("APP_USR-1672359003086280-100818-2d679c9c50811d51a48b4c7a42f58c7b-477455682");
    }
}
