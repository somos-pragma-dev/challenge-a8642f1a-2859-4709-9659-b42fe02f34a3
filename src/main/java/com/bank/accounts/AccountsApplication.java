package com.bank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Punto de entrada principal del microservicio de cuentas bancarias.
 * 
 * Esta aplicación implementa una arquitectura hexagonal con las siguientes capas:
 * - api: Controladores REST que exponen los endpoints del microservicio
 * - application: Servicios de aplicación que orquestan la lógica de negocio
 * - domain: Entidades, excepciones y contratos (repositorios) del dominio
 * - infrastructure: Implementaciones de persistencia, auditoría y manejo de errores
 * 
 * El microservicio gestiona operaciones de cuentas bancarias incluyendo:
 * - Creación de nuevas cuentas
 * - Consulta de saldos y información de cuentas
 * - Transferencias idempotentes entre cuentas
 * 
 * La idempotencia se implementa mediante claves basadas en número de operación y canal.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.bank.accounts"})
@EntityScan(basePackages = {"com.bank.accounts.domain.model"})
@EnableJpaRepositories(basePackages = {"com.bank.accounts.infrastructure.persistence"})
public class AccountsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}