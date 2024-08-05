package com.servidor.gestiondeventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.servidor.gestiondeventas.repository")
@ComponentScan(basePackages = "com.servidor.gestiondeventas")
public class GestionDeVentasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDeVentasApplication.class, args);
    }
    @PostConstruct
    public void init() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
    }
}

