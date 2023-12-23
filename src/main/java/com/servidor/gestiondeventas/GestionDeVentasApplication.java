package com.servidor.gestiondeventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.servidor.gestiondeventas.repository")
@ComponentScan(basePackages = "com.servidor.gestiondeventas")
public class GestionDeVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeVentasApplication.class, args);
	}

}

