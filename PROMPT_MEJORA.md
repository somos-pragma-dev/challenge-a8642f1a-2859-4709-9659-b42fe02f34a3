# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/accounts/application/TransferService.java` — `IdempotencyRecord`: IdempotencyRecord se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.bank.accounts.infrastructure.idempotency.IdempotencyRecord.
- `src/main/java/com/bank/accounts/api/AccountController.java` — `AuditEventPublisher.publishAccountCreated`: Se invoca `publishAccountCreated` sobre `AuditEventPublisher`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/AccountController.java` — `AccountResponse.accountId`: Se invoca `accountId` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/AccountController.java` — `AccountResponse.accountNumber`: Se invoca `accountNumber` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/AccountController.java` — `AccountNotFoundException.getMessage`: Se invoca `getMessage` sobre `AccountNotFoundException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/AccountController.java` — `AuditEventPublisher.publishAccountAccessed`: Se invoca `publishAccountAccessed` sobre `AuditEventPublisher`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/AccountController.java` — `AuditEventPublisher.publishAccountDeactivated`: Se invoca `publishAccountDeactivated` sobre `AuditEventPublisher`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferRequest.originAccountId`: Se invoca `originAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferRequest.destinationAccountId`: Se invoca `destinationAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.originAccountId`: Se invoca `originAccountId` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.destinationAccountId`: Se invoca `destinationAccountId` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.amount`: Se invoca `amount` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.transferId`: Se invoca `transferId` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferService.getTransfer`: Se invoca `getTransfer` sobre `TransferService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferService.getTransfersByAccount`: Se invoca `getTransfersByAccount` sobre `TransferService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferService.getAllTransfers`: Se invoca `getAllTransfers` sobre `TransferService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.accountNumber`: Se invoca `accountNumber` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.accountType`: Se invoca `accountType` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.currency`: Se invoca `currency` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.ownerDocument`: Se invoca `ownerDocument` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.ownerName`: Se invoca `ownerName` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/application/TransferService.java` — `TransferRequest.amount`: Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `Transfer.getSourceAccountNumber`: Se invoca `getSourceAccountNumber` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `Transfer.getTargetAccountNumber`: Se invoca `getTargetAccountNumber` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `Transfer.getExternalReference`: Se invoca `getExternalReference` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `AuditEvent.getEventType`: Se invoca `getEventType` sobre `AuditEvent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `AuditEvent.getEventId`: Se invoca `getEventId` sobre `AuditEvent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.id`: Se invoca `id` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.accountNumber`: Se invoca `accountNumber` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.balance`: Se invoca `balance` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.currency`: Se invoca `currency` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.size`: Se invoca `size` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.get`: Se invoca `get` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Crear un microservicio REST con Spring Boot, JPA y H2

### Reto
- Tema: Microservicio REST en entorno de banca
- Seniority: junior-l1
- Tipo: practical
- Título: Desarrollo de un microservicio de gestión de cuentas bancarias
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Crear y consultar cuentas — objetivo: Implementar la funcionalidad para crear y consultar cuentas bancarias. — entregable (NO resolver): Microservicio REST operativo que permite crear y consultar cuentas bancarias.
- Fase 2: Realizar transferencias entre cuentas — objetivo: Implementar la funcionalidad para realizar transferencias entre cuentas bancarias. — entregable (NO resolver): Microservicio REST extendido que permite realizar transferencias idempotentes entre cuentas bancarias.
- Fase 3: Optimización y refactorización — objetivo: Optimizar y refactorizar el microservicio para mejorar su rendimiento y mantenibilidad. — entregable (NO resolver): Microservicio REST optimizado y refactorizado, con documentación de las mejoras realizadas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>accounts</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>accounts</name>
    <description>Microservicio de gestión de cuentas bancarias</description>
    
    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <lombok.version>1.18.30</lombok.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/accounts/AccountsApplication.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  shutdown: graceful

spring:
  application:
    name: accounts-service
  
  datasource:
    url: jdbc:h2:mem:accountsdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 300000
      connection-timeout: 20000
      max-lifetime: 1200000
  
  h2:
    console:
      enabled: true
      path: /h2-console
  
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: false
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
    defer-datasource-initialization: true
  
  sql:
    init:
      mode: never
      data-locations: classpath:data.sql

logging:
  level:
    root: INFO
    com.bank.accounts: DEBUG
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/accounts-service.log
    max-size: 10MB
    max-history: 30


// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/AccountRequest.java ===
package com.bank.accounts.api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Currency;

@Builder
public record AccountRequest(
    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres")
    String accountNumber,

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del titular debe tener entre 3 y 100 caracteres")
    String holderName,

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(regexp = "^(AHORRO|CORRIENTE)$", message = "El tipo de cuenta debe ser AHORRO o CORRIENTE")
    String accountType,

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    @Digits(integer = 15, fraction = 2, message = "El saldo debe tener como máximo 15 dígitos enteros y 2 decimales")
    BigDecimal initialBalance,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe ser un código de 3 letras (ej: USD, EUR)")
    String currency,

    @NotBlank(message = "La identificación del cliente es obligatoria")
    @Size(min = 5, max = 20, message = "La identificación del cliente debe tener entre 5 y 20 caracteres")
    String clientIdentification
) {
    public AccountRequest {
        if (currency != null) {
            currency = currency.toUpperCase();
        }
    }

    public BigDecimal getInitialBalanceOrZero() {
        return initialBalance != null ? initialBalance : BigDecimal.ZERO;
    }

    public boolean isInitialBalanceValid() {
        return initialBalance != null && initialBalance.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isCurrencySupported() {
        if (currency == null) {
            return false;
        }
        try {
            Currency.getInstance(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/AccountResponse.java ===
package com.bank.accounts.api.dto;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Builder
@With
public record AccountResponse(
    String id,
    String accountNumber,
    String holderName,
    String accountType,
    BigDecimal balance,
    String currency,
    String status,
    Instant createdAt,
    Instant lastModifiedAt,
    String clientIdentification
) {
    public boolean isActive() {
        return "ACTIVA".equalsIgnoreCase(status);
    }

    public boolean isInactive() {
        return "INACTIVA".equalsIgnoreCase(status);
    }

    public boolean isBlocked() {
        return "BLOQUEADA".equalsIgnoreCase(status);
    }

    public boolean hasPositiveBalance() {
        return balance != null && balance.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        if (balance == null || amount == null) {
            return false;
        }
        return balance.compareTo(amount) >= 0;
    }

    public String getFormattedBalance() {
        if (balance == null) {
            return "0.00";
        }
        return String.format("%s %.2f", currency != null ? currency : "USD", balance);
    }

    public String getAccountTypeDescription() {
        return switch (accountType != null ? accountType.toUpperCase() : "") {
            case "AHORRO" -> "Cuenta de Ahorros";
            case "CORRIENTE" -> "Cuenta Corriente";
            default -> "Tipo Desconocido";
        };
    }

    public String getStatusDescription() {
        return switch (status != null ? status.toUpperCase() : "") {
            case "ACTIVA" -> "Cuenta activa y disponible";
            case "INACTIVA" -> "Cuenta inactiva";
            case "BLOQUEADA" -> "Cuenta bloqueada por seguridad";
            case "CERRADA" -> "Cuenta cerrada definitivamente";
            default -> "Estado desconocido";
        };
    }

    public long getDaysSinceCreation() {
        if (createdAt == null) {
            return 0;
        }
        return java.time.Duration.between(createdAt, Instant.now()).toDays();
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/TransferRequest.java ===
package com.bank.accounts.api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferRequest(
    @NotBlank(message = "El número de cuenta de origen es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta de origen debe tener entre 10 y 20 caracteres")
    String sourceAccountNumber,

    @NotBlank(message = "El número de cuenta de destino es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta de destino debe tener entre 10 y 20 caracteres")
    String destinationAccountNumber,

    @NotNull(message = "El monto de la transferencia es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @DecimalMax(value = "999999999.99", message = "El monto no puede exceder 999,999,999.99")
    @Digits(integer = 12, fraction = 2, message = "El monto debe tener como máximo 12 dígitos enteros y 2 decimales")
    BigDecimal amount,

    @NotBlank(message = "La descripción de la transferencia es obligatoria")
    @Size(min = 1, max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
    String description,

    @NotBlank(message = "La clave de idempotencia es obligatoria")
    @Size(min = 1, max = 64, message = "La clave de idempotencia debe tener entre 1 y 64 caracteres")
    String idempotencyKey,

    @NotBlank(message = "El canal de origen es obligatorio")
    @Pattern(regexp = "^(API_WEB|API_MOVIL|CAJERO| SUCURSAL|BATCH)$", 
              message = "El canal debe ser: API_WEB, API_MOVIL, CAJERO, SUCURSAL o BATCH")
    String channel,

    @Size(max = 50, message = "La referencia externa no puede exceder 50 caracteres")
    String externalReference
) {
    public TransferRequest {
        if (sourceAccountNumber != null) {
            sourceAccountNumber = sourceAccountNumber.trim();
        }
        if (destinationAccountNumber != null) {
            destinationAccountNumber = destinationAccountNumber.trim();
        }
        if (idempotencyKey != null) {
            idempotencyKey = idempotencyKey.trim();
        }
        if (channel != null) {
            channel = channel.toUpperCase();
        }
    }

    public boolean isValidAmount() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isSameAccount() {
        return sourceAccountNumber != null && sourceAccountNumber.equals(destinationAccountNumber);
    }

    public boolean hasExternalReference() {
        return externalReference != null && !externalReference.isBlank();
    }

    public String generateOperationKey() {
        return idempotencyKey + "-" + channel;
    }

    public BigDecimal getAmountWithFee() {
        BigDecimal fee = calculateFee();
        return amount.add(fee);
    }

    private BigDecimal calculateFee() {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal feePercentage = switch (channel != null ? channel : "") {
            case "API_WEB" -> new BigDecimal("0.005");
            case "API_MOVIL" -> new BigDecimal("0.003");
            case "CAJERO" -> new BigDecimal("0.010");
            case "SUCURSAL" -> new BigDecimal("0.015");
            case "BATCH" -> new BigDecimal("0.001");
            default -> new BigDecimal("0.005");
        };
        return amount.multiply(feePercentage).setScale(2, java.math.RoundingMode.HALF_UP);
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/TransferResponse.java ===
package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferResponse(
    UUID id,
    UUID sourceAccountId,
    UUID targetAccountId,
    BigDecimal amount,
    BigDecimal fee,
    BigDecimal totalAmount,
    String status,
    String statusDescription,
    LocalDateTime createdAt,
    LocalDateTime completedAt,
    String idempotencyKey,
    String operationNumber
) {
    public TransferResponse {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la transferencia no puede ser nulo");
        }
        if (sourceAccountId == null) {
            throw new IllegalArgumentException("La cuenta de origen no puede ser nula");
        }
        if (targetAccountId == null) {
            throw new IllegalArgumentException("La cuenta de destino no puede ser nula");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de la transferencia debe ser mayor a cero");
        }
    }

    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }

    public boolean isPending() {
        return "PENDING".equals(status);
    }

    public boolean isFailed() {
        return "FAILED".equals(status);
    }

    public boolean isRejected() {
        return "REJECTED".equals(status);
    }

    public boolean hasFee() {
        return fee != null && fee.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getNetAmount() {
        if (fee == null) {
            return amount;
        }
        return amount.subtract(fee);
    }

    public String getFormattedAmount() {
        return String.format("$%,.2f", amount);
    }

    public String getFormattedFee() {
        if (fee == null || fee.compareTo(BigDecimal.ZERO) == 0) {
            return "Sin cargo";
        }
        return String.format("$%,.2f", fee);
    }

    public String getFormattedTotalAmount() {
        return String.format("$%,.2f", totalAmount);
    }

    public String getStatusDisplayName() {
        return switch (status) {
            case "COMPLETED" -> "Completada";
            case "PENDING" -> "Pendiente";
            case "FAILED" -> "Fallida";
            case "REJECTED" -> "Rechazada";
            case "PROCESSING" -> "Procesando";
            default -> "Desconocido";
        };
    }

    public long getProcessingTimeSeconds() {
        if (createdAt == null || completedAt == null) {
            return 0;
        }
        return java.time.Duration.between(createdAt, completedAt).getSeconds();
    }

    public boolean wasProcessedQuickly() {
        return getProcessingTimeSeconds() < 5;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/model/Account.java ===
package com.bank.accounts.domain.model;

import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.domain.exception.AccountNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private UUID id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private String status;
    private String ownerDocument;
    private String ownerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastTransactionAt;
    private int dailyTransactionCount;
    private BigDecimal dailyTotalAmount;

    public Account(UUID id, String accountNumber, String accountType, BigDecimal balance,
                   String currency, String status, String ownerDocument, String ownerName,
                   LocalDateTime createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance != null ? balance : BigDecimal.ZERO;
        this.currency = currency != null ? currency : "USD";
        this.status = status != null ? status : "ACTIVE";
        this.ownerDocument = ownerDocument;
        this.ownerName = ownerName;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.lastTransactionAt = null;
        this.dailyTransactionCount = 0;
        this.dailyTotalAmount = BigDecimal.ZERO;
    }

    public void credit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor a cero");
        }
        if (!isActive()) {
            throw new IllegalStateException("No se puede acreditar a una cuenta inactiva");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
        this.lastTransactionAt = LocalDateTime.now();
        this.dailyTransactionCount++;
        this.dailyTotalAmount = this.dailyTotalAmount.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor a cero");
        }
        if (!isActive()) {
            throw new IllegalStateException("No se puede debitar de una cuenta inactiva");
        }
        if (!hasSufficientBalance(amount)) {
            throw new InsufficientBalanceException(
                String.format("Saldo insuficiente. Disponible: %s, Solicitado: %s", 
                    getFormattedBalance(), amount)
            );
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
        this.lastTransactionAt = LocalDateTime.now();
        this.dailyTransactionCount++;
        this.dailyTotalAmount = this.dailyTotalAmount.add(amount);
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        return this.balance.compareTo(amount) >= 0;
    }

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    public boolean isInactive() {
        return "INACTIVE".equalsIgnoreCase(status);
    }

    public boolean isBlocked() {
        return "BLOCKED".equalsIgnoreCase(status);
    }

    public void activate() {
        this.status = "ACTIVE";
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = "INACTIVE";
        this.updatedAt = LocalDateTime.now();
    }

    public void block() {
        this.status = "BLOCKED";
        this.updatedAt = LocalDateTime.now();
    }

    public void resetDailyLimits() {
        this.dailyTransactionCount = 0;
        this.dailyTotalAmount = BigDecimal.ZERO;
    }

    public boolean hasReachedDailyLimit(int maxTransactions) {
        return this.dailyTransactionCount >= maxTransactions;
    }

    public boolean hasReachedDailyAmountLimit(BigDecimal maxAmount) {
        return this.dailyTotalAmount.compareTo(maxAmount) >= 0;
    }

    public String getFormattedBalance() {
        return String.format("%s %s", currency, balance.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    public String getAccountTypeDescription() {
        return switch (accountType != null ? accountType.toUpperCase() : "UNKNOWN") {
            case "CHECKING" -> "Cuenta Corriente";
            case "SAVINGS" -> "Cuenta de Ahorros";
            case "SALARY" -> "Cuenta de Nómina";
            case "FIXED_TERM" -> "Depósito a Plazo";
            default -> "Tipo Desconocido";
        };
    }

    public String getStatusDescription() {
        return switch (status != null ? status.toUpperCase() : "UNKNOWN") {
            case "ACTIVE" -> "Activa";
            case "INACTIVE" -> "Inactiva";
            case "BLOCKED" -> "Bloqueada";
            case "CLOSED" -> "Cerrada";
            default -> "Estado Desconocido";
        };
    }

    public long getDaysSinceCreation() {
        if (createdAt == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(createdAt, LocalDateTime.now());
    }

    public boolean isNewAccount() {
        return getDaysSinceCreation() < 30;
    }

    public boolean hasPositiveBalance() {
        return balance != null && balance.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean hasZeroBalance() {
        return balance != null && balance.compareTo(BigDecimal.ZERO) == 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwnerDocument() {
        return ownerDocument;
    }

    public void setOwnerDocument(String ownerDocument) {
        this.ownerDocument = ownerDocument;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastTransactionAt() {
        return lastTransactionAt;
    }

    public void setLastTransactionAt(LocalDateTime lastTransactionAt) {
        this.lastTransactionAt = lastTransactionAt;
    }

    public int getDailyTransactionCount() {
        return dailyTransactionCount;
    }

    public void setDailyTransactionCount(int dailyTransactionCount) {
        this.dailyTransactionCount = dailyTransactionCount;
    }

    public BigDecimal getDailyTotalAmount() {
        return dailyTotalAmount;
    }

    public void setDailyTotalAmount(BigDecimal dailyTotalAmount) {
        this.dailyTotalAmount = dailyTotalAmount;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/model/Transfer.java ===
package com.bank.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {
    private UUID id;
    private UUID sourceAccountId;
    private UUID targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status;
    private String channel;
    private String idempotencyKey;
    private String operationNumber;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime failedAt;
    private String failureReason;
    private int retryCount;
    private static final int MAX_RETRY_COUNT = 3;

    public Transfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount,
                    String channel, String idempotencyKey) {
        this.id = UUID.randomUUID();
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount != null ? amount : BigDecimal.ZERO;
        this.fee = calculateFee(amount);
        this.status = "PENDING";
        this.channel = channel != null ? channel : "API";
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = generateOperationNumber();
        this.description = "";
        this.createdAt = LocalDateTime.now();
        this.retryCount = 0;
    }

    private BigDecimal calculateFee(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal feeThreshold = new BigDecimal("1000.00");
        BigDecimal percentageFee = new BigDecimal("0.01");
        if (amount.compareTo(feeThreshold) > 0) {
            return amount.multiply(percentageFee).setScale(2, java.math.RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    private String generateOperationNumber() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 10000);
        return String.format("OP%08d%04d", timestamp, random);
    }

    public static String generateIdempotencyKey(String channel, String operationNumber) {
        if (channel == null || operationNumber == null) {
            throw new IllegalArgumentException("El canal y el número de operación son obligatorios para generar la clave de idempotencia");
        }
        return channel.toUpperCase() + "-" + operationNumber;
    }

    public boolean isPending() {
        return "PENDING".equals(status);
    }

    public boolean isProcessing() {
        return "PROCESSING".equals(status);
    }

    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }

    public boolean isFailed() {
        return "FAILED".equals(status);
    }

    public boolean isRejected() {
        return "REJECTED".equals(status);
    }

    public void markAsProcessing() {
        if (!isPending()) {
            throw new IllegalStateException("Solo se puede procesar una transferencia en estado PENDING");
        }
        this.status = "PROCESSING";
    }

    public void markAsCompleted() {
        if (!isProcessing()) {
            throw new IllegalStateException("Solo se puede completar una transferencia en estado PROCESSING");
        }
        this.status = "COMPLETED";
        this.completedAt = LocalDateTime.now();
    }

    public void markAsFailed(String reason) {
        if (isCompleted()) {
            throw new IllegalStateException("No se puede marcar como fallida una transferencia ya completada");
        }
        this.status = "FAILED";
        this.failedAt = LocalDateTime.now();
        this.failureReason = reason;
    }

    public void markAsRejected(String reason) {
        if (!isPending() && !isProcessing()) {
            throw new IllegalStateException("Solo se puede rechazar una transferencia en estado PENDING o PROCESSING");
        }
        this.status = "REJECTED";
        this.failedAt = LocalDateTime.now();
        this.failureReason = reason;
    }

    public boolean canRetry() {
        return isFailed() && retryCount < MAX_RETRY_COUNT;
    }

    public void incrementRetryCount() {
        if (retryCount >= MAX_RETRY_COUNT) {
            throw new IllegalStateException("Se ha alcanzado el número máximo de reintentos");
        }
        this.retryCount++;
        this.status = "PENDING";
    }

    public boolean isIdempotent(String newIdempotencyKey) {
        if (idempotencyKey == null || newIdempotencyKey == null) {
            return false;
        }
        return idempotencyKey.equals(newIdempotencyKey);
    }

    public BigDecimal getTotalAmount() {
        if (fee == null) {
            return amount;
        }
        return amount.add(fee);
    }

    public boolean isSameAccount() {
        return sourceAccountId != null && sourceAccountId.equals(targetAccountId);
    }

    public boolean isValidAmount() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isInterbank() {
        return false;
    }

    public boolean isHighValue() {
        BigDecimal highValueThreshold = new BigDecimal("10000.00");
        return amount.compareTo(highValueThreshold) >= 0;
    }

    public long getProcessingTimeSeconds() {
        if (createdAt == null || completedAt == null) {
            return 0;
        }
        return java.time.Duration.between(createdAt, completedAt).getSeconds();
    }

    public String getStatusDescription() {
        return switch (status != null ? status : "UNKNOWN") {
            case "PENDING" -> "Pendiente de procesamiento";
            case "PROCESSING" -> "Procesando";
            case "COMPLETED" -> "Completada exitosamente";
            case "FAILED" -> "Fallida";
            case "REJECTED" -> "Rechazada";
            default -> "Estado desconocido";
        };
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(UUID sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public UUID getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(UUID targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(LocalDateTime failedAt) {
        this.failedAt = failedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/repository/AccountRepository.java ===
package com.bank.accounts.domain.repository;

import com.bank.accounts.domain.model.Account;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Contrato para la persistencia de cuentas bancarias.
 * Define las operaciones de acceso a datos que el dominio necesita.
 */
public interface AccountRepository {
    
    /**
     * Busca una cuenta por su identificador único.
     * @param id el identificador de la cuenta
     * @return Optional contendo la cuenta si existe
     */
    Optional<Account> findById(UUID id);
    
    /**
     * Busca una cuenta por su número de cuenta.
     * @param accountNumber el número de cuenta
     * @return Optional contendo la cuenta si existe
     */
    Optional<Account> findByAccountNumber(String accountNumber);
    
    /**
     * Busca todas las cuentas de un cliente específico.
     * @param customerId el identificador del cliente
     * @return lista de cuentas del cliente
     */
    List<Account> findByCustomerId(String customerId);
    
    /**
     * Busca todas las cuentas activas de un cliente.
     * @param customerId el identificador del cliente
     * @return lista de cuentas activas del cliente
     */
    List<Account> findActiveByCustomerId(String customerId);
    
    /**
     * Busca cuentas que coincidan con el filtro de estado.
     * @param active true para activas, false para inactivas
     * @return lista de cuentas que coinciden
     */
    List<Account> findByActive(boolean active);
    
    /**
     * Persiste una nueva cuenta en el repositorio.
     * @param account la cuenta a guardar
     * @return la cuenta persistida con su ID asignado
     */
    Account save(Account account);
    
    /**
     * Actualiza una cuenta existente.
     * @param account la cuenta con los datos actualizados
     * @return la cuenta actualizada
     */
    Account update(Account account);
    
    /**
     * Elimina una cuenta por su identificador.
     * @param id el identificador de la cuenta a eliminar
     */
    void deleteById(UUID id);
    
    /**
     * Verifica si existe una cuenta con el número dado.
     * @param accountNumber el número de cuenta a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByAccountNumber(String accountNumber);
    
    /**
     * Verifica si existe una cuenta con el ID dado.
     * @param id el identificador a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(UUID id);
    
    /**
     * Actualiza el saldo de una cuenta de forma atómica.
     * Utilizado para operaciones de débito/crédito.
     * @param accountId el identificador de la cuenta
     * @param newBalance el nuevo saldo
     * @return el número de filas afectadas
     */
    int updateBalance(UUID accountId, BigDecimal newBalance);
    
    /**
     * Bloquea una cuenta para evitar operaciones.
     * @param accountId el identificador de la cuenta
     * @return true si se bloqueó correctamente
     */
    boolean blockAccount(UUID accountId);
    
    /**
     * Desbloquea una cuenta previamente bloqueada.
     * @param accountId el identificador de la cuenta
     * @return true si se desbloqueó correctamente
     */
    boolean unblockAccount(UUID accountId);
    
    /**
     * Cuenta el número total de cuentas en el repositorio.
     * @return cantidad total de cuentas
     */
    long count();
    
    /**
     * Busca cuentas por tipo específico.
     * @param accountType el tipo de cuenta (AHORRO, CORRIENTE, etc.)
     * @return lista de cuentas del tipo especificado
     */
    List<Account> findByAccountType(String accountType);
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/persistence/AccountJpaRepository.java ===
package com.bank.accounts.infrastructure.persistence;

import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación JPA del repositorio de cuentas.
 * Utiliza H2 como base de datos en memoria para el entorno de desarrollo.
 */
@Repository
@Transactional
public class AccountJpaRepository implements AccountRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Optional<Account> findById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        return Optional.ofNullable(account);
    }
    
    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber",
            Account.class
        );
        query.setParameter("accountNumber", accountNumber);
        
        List<Account> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
    
    @Override
    public List<Account> findByCustomerId(String customerId) {
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.customerId = :customerId",
            Account.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
    @Override
    public List<Account> findActiveByCustomerId(String customerId) {
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.customerId = :customerId AND a.active = true",
            Account.class
        );
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
    @Override
    public List<Account> findByActive(boolean active) {
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.active = :active",
            Account.class
        );
        query.setParameter("active", active);
        return query.getResultList();
    }
    
    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setCreatedAt(LocalDateTime.now());
            entityManager.persist(account);
            return account;
        } else {
            account.setUpdatedAt(LocalDateTime.now());
            return entityManager.merge(account);
        }
    }
    
    @Override
    public Account update(Account account) {
        account.setUpdatedAt(LocalDateTime.now());
        return entityManager.merge(account);
    }
    
    @Override
    public void deleteById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }
    
    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        Query query = entityManager.createQuery(
            "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber"
        );
        query.setParameter("accountNumber", accountNumber);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
    
    @Override
    public boolean existsById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        return account != null;
    }
    
    @Override
    public int updateBalance(UUID accountId, BigDecimal newBalance) {
        Query query = entityManager.createQuery(
            "UPDATE Account a SET a.balance = :newBalance, a.updatedAt = :updatedAt " +
            "WHERE a.id = :id"
        );
        query.setParameter("newBalance", newBalance);
        query.setParameter("updatedAt", LocalDateTime.now());
        query.setParameter("id", accountId);
        return query.executeUpdate();
    }
    
    @Override
    public boolean blockAccount(UUID accountId) {
        Query query = entityManager.createQuery(
            "UPDATE Account a SET a.blocked = true, a.status = 'BLOCKED', " +
            "a.updatedAt = :updatedAt WHERE a.id = :id"
        );
        query.setParameter("updatedAt", LocalDateTime.now());
        query.setParameter("id", accountId);
        int updated = query.executeUpdate();
        return updated > 0;
    }
    
    @Override
    public boolean unblockAccount(UUID accountId) {
        Query query = entityManager.createQuery(
            "UPDATE Account a SET a.blocked = false, a.status = 'ACTIVE', " +
            "a.updatedAt = :updatedAt WHERE a.id = :id"
        );
        query.setParameter("updatedAt", LocalDateTime.now());
        query.setParameter("id", accountId);
        int updated = query.executeUpdate();
        return updated > 0;
    }
    
    @Override
    public long count() {
        Query query = entityManager.createQuery("SELECT COUNT(a) FROM Account a");
        return (Long) query.getSingleResult();
    }
    
    @Override
    public List<Account> findByAccountType(String accountType) {
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.accountType = :accountType",
            Account.class
        );
        query.setParameter("accountType", accountType);
        return query.getResultList();
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/exception/InsufficientBalanceException.java ===
package com.bank.accounts.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Excepción lanzada cuando una cuenta no tiene saldo suficiente para realizar una operación.
 * Esta excepción indica un error de negocio recuperable que debe ser manejado por el controller
 * mediante una respuesta HTTP 400 (Bad Request) con un mensaje claro para el cliente.
 */
public class InsufficientBalanceException extends RuntimeException {
    
    private final UUID accountId;
    private final String accountNumber;
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final BigDecimal shortfall;
    
    /**
     * Constructor con todos los detalles de la operación fallida.
     * @param accountId identificador de la cuenta
     * @param accountNumber número de la cuenta
     * @param currentBalance saldo actual de la cuenta
     * @param requestedAmount monto solicitado para la operación
     */
    public InsufficientBalanceException(
            UUID accountId,
            String accountNumber,
            BigDecimal currentBalance,
            BigDecimal requestedAmount) {
        super(buildMessage(accountNumber, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.shortfall = requestedAmount.subtract(currentBalance);
    }
    
    /**
     * Constructor simplificado para casos donde no se conoce el ID de cuenta.
     * @param message mensaje de error personalizado
     */
    public InsufficientBalanceException(String message) {
        super(message);
        this.accountId = null;
        this.accountNumber = null;
        this.currentBalance = BigDecimal.ZERO;
        this.requestedAmount = BigDecimal.ZERO;
        this.shortfall = BigDecimal.ZERO;
    }
    
    private static String buildMessage(String accountNumber, BigDecimal current, BigDecimal requested) {
        return String.format(
            "Saldo insuficiente en cuenta %s: saldo actual=%s, monto solicitado=%s",
            accountNumber,
            current.toPlainString(),
            requested.toPlainString()
        );
    }
    
    public UUID getAccountId() {
        return accountId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
    
    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }
    
    public BigDecimal getShortfall() {
        return shortfall;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/exception/AccountNotFoundException.java ===
package com.bank.accounts.domain.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String accountId;
    private final String accountNumber;
    private final LocalDateTime occurredAt;
    private final String errorCode;
    private final Map<String, Object> context;
    
    public AccountNotFoundException(String accountId) {
        super(buildDefaultMessage(accountId, null));
        this.accountId = accountId;
        this.accountNumber = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    public AccountNotFoundException(String accountId, String accountNumber) {
        super(buildDefaultMessage(accountId, accountNumber));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    public AccountNotFoundException(String accountId, String accountNumber, String customMessage) {
        super(customMessage);
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    public AccountNotFoundException(String accountId, Throwable cause) {
        super(buildDefaultMessage(accountId, null), cause);
        this.accountId = accountId;
        this.accountNumber = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "ACCOUNT_NOT_FOUND";
        this.context = new HashMap<>();
        initializeContext();
    }
    
    private static String buildDefaultMessage(String accountId, String accountNumber) {
        if (accountNumber != null && !accountNumber.isEmpty()) {
            return String.format("Cuenta no encontrada: ID=%s, Número de cuenta=%s", accountId, accountNumber);
        }
        return String.format("Cuenta no encontrada con ID: %s", accountId);
    }
    
    private void initializeContext() {
        this.context.put("accountId", this.accountId);
        this.context.put("accountNumber", this.accountNumber);
        this.context.put("occurredAt", this.occurredAt.format(FORMATTER));
        this.context.put("errorCode", this.errorCode);
        this.context.put("traceId", UUID.randomUUID().toString());
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public Map<String, Object> getContext() {
        return Map.copyOf(context);
    }
    
    public String getFormattedOccurredAt() {
        return occurredAt.format(FORMATTER);
    }
    
    public boolean hasAccountNumber() {
        return accountNumber != null && !accountNumber.isEmpty();
    }
    
    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccountNotFoundException{\n");
        sb.append("  errorCode: ").append(errorCode).append("\n");
        sb.append("  accountId: ").append(accountId).append("\n");
        if (hasAccountNumber()) {
            sb.append("  accountNumber: ").append(accountNumber).append("\n");
        }
        sb.append("  occurredAt: ").append(getFormattedOccurredAt()).append("\n");
        sb.append("  traceId: ").append(context.get("traceId")).append("\n");
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getDetailedMessage();
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/exception/DuplicateAccountException.java ===
package com.bank.accounts.domain.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DuplicateAccountException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private final String accountNumber;
    private final String accountHolderId;
    private final LocalDateTime occurredAt;
    private final String errorCode;
    private final Map<String, Object> context;
    private final LocalDateTime existingAccountCreatedAt;
    
    public DuplicateAccountException(String accountNumber) {
        super(buildDefaultMessage(accountNumber, null));
        this.accountNumber = accountNumber;
        this.accountHolderId = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, String accountHolderId) {
        super(buildDefaultMessage(accountNumber, accountHolderId));
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, String accountHolderId, LocalDateTime existingAccountCreatedAt) {
        super(buildDetailedMessage(accountNumber, accountHolderId, existingAccountCreatedAt));
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = existingAccountCreatedAt;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, String accountHolderId, String customMessage) {
        super(customMessage);
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    public DuplicateAccountException(String accountNumber, Throwable cause) {
        super(buildDefaultMessage(accountNumber, null), cause);
        this.accountNumber = accountNumber;
        this.accountHolderId = null;
        this.occurredAt = LocalDateTime.now();
        this.errorCode = "DUPLICATE_ACCOUNT";
        this.context = new HashMap<>();
        this.existingAccountCreatedAt = null;
        initializeContext();
    }
    
    private static String buildDefaultMessage(String accountNumber, String accountHolderId) {
        if (accountHolderId != null && !accountHolderId.isEmpty()) {
            return String.format("Ya existe una cuenta con el número: %s para el titular: %s", accountNumber, accountHolderId);
        }
        return String.format("Ya existe una cuenta con el número: %s", accountNumber);
    }
    
    private static String buildDetailedMessage(String accountNumber, String accountHolderId, LocalDateTime existingCreatedAt) {
        String baseMessage = buildDefaultMessage(accountNumber, accountHolderId);
        if (existingCreatedAt != null) {
            return baseMessage + ". La cuenta existente fue creada el: " + existingCreatedAt.format(FORMATTER);
        }
        return baseMessage;
    }
    
    private void initializeContext() {
        this.context.put("accountNumber", this.accountNumber);
        this.context.put("accountHolderId", this.accountHolderId);
        this.context.put("occurredAt", this.occurredAt.format(FORMATTER));
        this.context.put("errorCode", this.errorCode);
        this.context.put("traceId", UUID.randomUUID().toString());
        if (existingAccountCreatedAt != null) {
            this.context.put("existingAccountCreatedAt", existingAccountCreatedAt.format(FORMATTER));
        }
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderId() {
        return accountHolderId;
    }
    
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public Map<String, Object> getContext() {
        return Map.copyOf(context);
    }
    
    public LocalDateTime getExistingAccountCreatedAt() {
        return existingAccountCreatedAt;
    }
    
    public String getFormattedOccurredAt() {
        return occurredAt.format(FORMATTER);
    }
    
    public boolean hasAccountHolderId() {
        return accountHolderId != null && !accountHolderId.isEmpty();
    }
    
    public boolean hasExistingAccountInfo() {
        return existingAccountCreatedAt != null;
    }
    
    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("DuplicateAccountException{\n");
        sb.append("  errorCode: ").append(errorCode).append("\n");
        sb.append("  accountNumber: ").append(accountNumber).append("\n");
        if (hasAccountHolderId()) {
            sb.append("  accountHolderId: ").append(accountHolderId).append("\n");
        }
        if (hasExistingAccountInfo()) {
            sb.append("  existingAccountCreatedAt: ").append(existingAccountCreatedAt.format(FORMATTER)).append("\n");
        }
        sb.append("  occurredAt: ").append(getFormattedOccurredAt()).append("\n");
        sb.append("  traceId: ").append(context.get("traceId")).append("\n");
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getDetailedMessage();
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyRepository.java ===
package com.bank.accounts.infrastructure.idempotency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IdempotencyRepository {
    
    boolean existsByOperationKey(String operationKey);
    
    void save(IdempotencyRecord record);
    
    Optional<IdempotencyRecord> findByOperationKey(String operationKey);
    
    void deleteByOperationKey(String operationKey);
    
    void deleteExpiredRecords(LocalDateTime cutoffDate);
    
    long countByChannelAndCreatedAfter(String channel, LocalDateTime after);
    
    List<IdempotencyRecord> findByChannelAndCreatedBetween(String channel, LocalDateTime start, LocalDateTime end);
    
    record IdempotencyRecord(
        String operationKey,
        String channel,
        String requestHash,
        String responseStatus,
        String responseBody,
        LocalDateTime createdAt,
        LocalDateTime expiresAt,
        int retryCount
    ) {
        public IdempotencyRecord {
            if (operationKey == null || operationKey.isBlank()) {
                throw new IllegalArgumentException("operationKey no puede ser nulo o vacío");
            }
            if (channel == null || channel.isBlank()) {
                throw new IllegalArgumentException("channel no puede ser nulo o vacío");
            }
            if (createdAt == null) {
                throw new IllegalArgumentException("createdAt no puede ser nulo");
            }
            if (expiresAt == null) {
                throw new IllegalArgumentException("expiresAt no puede ser nulo");
            }
            if (expiresAt.isBefore(createdAt)) {
                throw new IllegalArgumentException("expiresAt no puede ser anterior a createdAt");
            }
        }
        
        public static IdempotencyRecord create(String operationKey, String channel, String requestHash) {
            LocalDateTime now = LocalDateTime.now();
            return new IdempotencyRecord(
                operationKey,
                channel,
                requestHash,
                null,
                null,
                now,
                now.plusHours(24),
                0
            );
        }
        
        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expiresAt);
        }
        
        public boolean isExpiredAt(LocalDateTime time) {
            return time.isAfter(expiresAt);
        }
        
        public IdempotencyRecord withResponse(String status, String body) {
            return new IdempotencyRecord(
                this.operationKey,
                this.channel,
                this.requestHash,
                status,
                body,
                this.createdAt,
                this.expiresAt,
                this.retryCount
            );
        }
        
        public IdempotencyRecord withIncrementedRetry() {
            return new IdempotencyRecord(
                this.operationKey,
                this.channel,
                this.requestHash,
                this.responseStatus,
                this.responseBody,
                this.createdAt,
                this.expiresAt,
                this.retryCount + 1
            );
        }
        
        public IdempotencyRecord withExtendedExpiration(LocalDateTime newExpiration) {
            if (newExpiration.isBefore(this.expiresAt)) {
                throw new IllegalArgumentException("La nueva fecha de expiración no puede ser anterior a la actual");
            }
            return new IdempotencyRecord(
                this.operationKey,
                this.channel,
                this.requestHash,
                this.responseStatus,
                this.responseBody,
                this.createdAt,
                newExpiration,
                this.retryCount
            );
        }
        
        public boolean hasResponse() {
            return responseStatus != null && responseBody != null;
        }
        
        public long getTtlSeconds() {
            return java.time.Duration.between(LocalDateTime.now(), expiresAt).getSeconds();
        }
        
        public long getTtlSecondsAt(LocalDateTime time) {
            return java.time.Duration.between(time, expiresAt).getSeconds();
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/AccountController.java ===
package com.bank.accounts.api;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.application.AccountService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final AuditEventPublisher auditPublisher;

    public AccountController(AccountService accountService, AuditEventPublisher auditPublisher) {
        this.accountService = accountService;
        this.auditPublisher = auditPublisher;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        logger.info("Recibida solicitud de creacion de cuenta");
        
        if (!request.isInitialBalanceValid()) {
            logger.warn("Balance inicial invalido: {}", request.getInitialBalanceOrZero());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El balance inicial no puede ser negativo");
        }
        
        if (!request.isCurrencySupported()) {
            logger.warn("Moneda no soportada en solicitud");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Moneda no soportada");
        }

        try {
            AccountResponse response = accountService.createAccount(request);
            auditPublisher.publishAccountCreated(response.accountId(), response.accountNumber());
            logger.info("Cuenta creada exitosamente con ID: {}", response.accountId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateAccountException e) {
            logger.error("Duplicacion de cuenta detectada: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
        logger.info("Consultando cuenta con ID: {}", accountId);
        
        if (accountId == null || accountId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de cuenta invalido");
        }

        try {
            AccountResponse response = accountService.getAccount(accountId);
            auditPublisher.publishAccountAccessed(accountId);
            return ResponseEntity.ok(response);
        } catch (AccountNotFoundException e) {
            logger.warn("Cuenta no encontrada: {}", accountId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String currency) {
        logger.info("Listando todas las cuentas - filtro status: {}, currency: {}", status, currency);
        
        List<AccountResponse> accounts = accountService.getAllAccounts(status, currency);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountId) {
        logger.info("Consultando balance de cuenta: {}", accountId);
        
        try {
            BigDecimal balance = accountService.getBalance(accountId);
            return ResponseEntity.ok(balance);
        } catch (AccountNotFoundException e) {
            logger.warn("Cuenta no encontrada para consulta de balance: {}", accountId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deactivateAccount(@PathVariable Long accountId) {
        logger.info("Solicitud de desactivacion de cuenta: {}", accountId);
        
        try {
            accountService.deactivateAccount(accountId);
            auditPublisher.publishAccountDeactivated(accountId);
            return ResponseEntity.noContent().build();
        } catch (AccountNotFoundException e) {
            logger.warn("Cuenta no encontrada para desactivacion: {}", accountId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/TransferController.java ===
package com.bank.accounts.api;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.application.TransferService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import com.bank.accounts.infrastructure.idempotency.IdempotencyKeyGenerator;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private final TransferService transferService;
    private final IdempotencyRepository idempotencyRepository;
    private final IdempotencyKeyGenerator keyGenerator;
    private final AuditEventPublisher auditPublisher;

    public TransferController(
            TransferService transferService,
            IdempotencyRepository idempotencyRepository,
            IdempotencyKeyGenerator keyGenerator,
            AuditEventPublisher auditPublisher) {
        this.transferService = transferService;
        this.idempotencyRepository = idempotencyRepository;
        this.keyGenerator = keyGenerator;
        this.auditPublisher = auditPublisher;
    }

    @PostMapping
    public ResponseEntity<TransferResponse> executeTransfer(
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody TransferRequest request) {
        
        logger.info("Recibida solicitud de transferencia - origen: {}, destino: {}, monto: {}",
                request.originAccountId(), request.destinationAccountId(), request.getAmountWithFee());

        if (!request.isValidAmount()) {
            logger.warn("Monto de transferencia invalido");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El monto de transferencia debe ser mayor a cero");
        }

        if (request.isSameAccount()) {
            logger.warn("Intento de transferencia a la misma cuenta");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede transferir a la misma cuenta");
        }

        String operationKey = (idempotencyKey != null && !idempotencyKey.isBlank())
                ? keyGenerator.generateKey(idempotencyKey, "TRANSFER")
                : request.generateOperationKey();

        if (idempotencyRepository.existsByOperationKey(operationKey)) {
            logger.info("Operacion idempotente detectada - clave: {}", operationKey);
            TransferResponse cachedResponse = idempotencyRepository.findByOperationKey(operationKey);
            if (cachedResponse != null) {
                return ResponseEntity.ok(cachedResponse);
            }
        }

        try {
            TransferResponse response = transferService.executeTransfer(request, operationKey);
            idempotencyRepository.save(operationKey, response, "TRANSFER");
            
            auditPublisher.publishTransferExecuted(
                    response.originAccountId(),
                    response.destinationAccountId(),
                    response.amount(),
                    operationKey);
            
            logger.info("Transferencia ejecutada exitosamente - ID: {}, clave idempotencia: {}",
                    response.transferId(), operationKey);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (AccountNotFoundException e) {
            logger.error("Cuenta no encontrada en transferencia: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InsufficientBalanceException e) {
            logger.warn("Saldo insuficiente para transferencia: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error al ejecutar transferencia: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la transferencia");
        }
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<TransferResponse> getTransfer(@PathVariable Long transferId) {
        logger.info("Consultando transferencia con ID: {}", transferId);
        
        try {
            TransferResponse response = transferService.getTransfer(transferId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.warn("Transferencia no encontrada: {}", transferId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia no encontrada");
        }
    }

    @GetMapping("/by-operation-key/{operationKey}")
    public ResponseEntity<TransferResponse> getTransferByOperationKey(@PathVariable String operationKey) {
        logger.info("Consultando transferencia por clave de operacion: {}", operationKey);
        
        TransferResponse response = idempotencyRepository.findByOperationKey(operationKey);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe transferencia con esta clave de operacion");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getTransfers(
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Instant fromDate,
            @RequestParam(required = false) Instant toDate) {
        
        logger.info("Listando transferencias - cuenta: {}, status: {}, rango: {} - {}",
                accountId, status, fromDate, toDate);
        
        if (accountId != null) {
            return ResponseEntity.ok(transferService.getTransfersByAccount(accountId));
        }
        
        return ResponseEntity.ok(transferService.getAllTransfers());
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/application/AccountService.java ===
package com.bank.accounts.application;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.persistence.AccountJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "MXN", "COP");

    private final AccountRepository accountRepository;
    private final AccountJpaRepository jpaRepository;

    public AccountService(AccountRepository accountRepository, AccountJpaRepository jpaRepository) {
        this.accountRepository = accountRepository;
        this.jpaRepository = jpaRepository;
    }

    @Transactional
    public AccountResponse createAccount(AccountRequest request) {
        logger.info("Iniciando creacion de cuenta con numero: {}", request.accountNumber());

        Optional<Account> existingAccount = accountRepository.findByAccountNumber(request.accountNumber());
        if (existingAccount.isPresent()) {
            logger.error("Ya existe una cuenta con el numero: {}", request.accountNumber());
            throw new DuplicateAccountException("Ya existe una cuenta con el numero: " + request.accountNumber());
        }

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(request.accountType());
        account.setCurrency(request.currency() != null ? request.currency() : "USD");
        account.setBalance(request.getInitialBalanceOrZero());
        account.setStatus(Account.AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDate.now());
        account.setLastModifiedAt(LocalDate.now());

        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.getId());

        return mapToResponse(savedAccount);
    }

    @Transactional(readOnly = true)
    public AccountResponse getAccount(Long accountId) {
        logger.debug("Consultando cuenta con ID: {}", accountId);
        
        return accountRepository.findById(accountId)
                .map(this::mapToResponse)
                .orElseThrow(() -> {
                    logger.warn("Cuenta no encontrada: {}", accountId);
                    return new AccountNotFoundException("Cuenta no encontrada con ID: " + accountId);
                });
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> getAllAccounts(String status, String currency) {
        logger.debug("Listando cuentas con filtros - status: {}, currency: {}", status, currency);
        
        List<Account> accounts;
        
        if (status != null && !status.isBlank()) {
            Account.AccountStatus accountStatus = Account.AccountStatus.valueOf(status.toUpperCase());
            accounts = accountRepository.findByStatus(accountStatus);
        } else if (currency != null && !currency.isBlank()) {
            accounts = accountRepository.findByCurrency(currency.toUpperCase());
        } else {
            accounts = accountRepository.findAll();
        }

        return accounts.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        logger.debug("Consultando balance de cuenta: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    logger.warn("Cuenta no encontrada para consulta de balance: {}", accountId);
                    return new AccountNotFoundException("Cuenta no encontrada con ID: " + accountId);
                });
        
        return account.getBalance();
    }

    @Transactional
    public void deactivateAccount(Long accountId) {
        logger.info("Desactivando cuenta: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    logger.warn("Cuenta no encontrada para desactivacion: {}", accountId);
                    return new AccountNotFoundException("Cuenta no encontrada con ID: " + accountId);
                });
        
        account.setStatus(Account.AccountStatus.INACTIVE);
        account.setLastModifiedAt(LocalDate.now());
        accountRepository.save(account);
        
        logger.info("Cuenta desactivada exitosamente: {}", accountId);
    }

    @Transactional
    public void updateBalance(Long accountId, BigDecimal newBalance) {
        logger.debug("Actualizando balance de cuenta {} a {}", accountId, newBalance);
        
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontrada con ID: " + accountId));
        
        account.setBalance(newBalance);
        account.setLastModifiedAt(LocalDate.now());
        accountRepository.save(account);
    }

    private String generateAccountNumber() {
        return "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private AccountResponse mapToResponse(Account account) {
        long daysSinceCreation = ChronoUnit.DAYS.between(account.getCreatedAt(), LocalDate.now());
        
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getCurrency(),
                account.getBalance(),
                account.getStatus().name(),
                account.getCreatedAt(),
                account.getLastModifiedAt()
        );
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/application/TransferService.java ===
package com.bank.accounts.application;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import com.bank.accounts.infrastructure.idempotency.IdempotencyKeyGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    private final AccountRepository accountRepository;
    private final IdempotencyRepository idempotencyRepository;
    private final IdempotencyKeyGenerator idempotencyKeyGenerator;
    private final AuditEventPublisher auditEventPublisher;
    private final AccountService accountService;

    public TransferService(
            AccountRepository accountRepository,
            IdempotencyRepository idempotencyRepository,
            IdempotencyKeyGenerator idempotencyKeyGenerator,
            AuditEventPublisher auditEventPublisher,
            AccountService accountService) {
        this.accountRepository = accountRepository;
        this.idempotencyRepository = idempotencyRepository;
        this.idempotencyKeyGenerator = idempotencyKeyGenerator;
        this.auditEventPublisher = auditEventPublisher;
        this.accountService = accountService;
    }

    @Transactional
    public TransferResponse executeTransfer(TransferRequest request, String channel) {
        logger.info("Iniciando transferencia: originador={}, destino={}, monto={}, canal={}",
                request.getSourceAccountId(), request.getTargetAccountId(), 
                request.getAmountWithFee(), channel);

        if (!request.isValidAmount()) {
            throw new IllegalArgumentException("El monto de transferencia debe ser mayor a cero");
        }

        if (request.isSameAccount()) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        String idempotencyKey = idempotencyKeyGenerator.generate(
                request.generateOperationKey(), channel);

        Optional<Transfer> existingTransfer = idempotencyRepository.findByKey(idempotencyKey);
        if (existingTransfer.isPresent()) {
            logger.info("Transferencia idempotente encontrada, retornando resultado anterior: {}", 
                    idempotencyKey);
            return mapToResponse(existingTransfer.get());
        }

        Account sourceAccount = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Cuenta origen no encontrada: " + request.getSourceAccountId()));

        Account targetAccount = accountRepository.findById(request.getTargetAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Cuenta destino no encontrada: " + request.getTargetAccountId()));

        if (!sourceAccount.hasSufficientBalance(request.getAmountWithFee())) {
            throw new InsufficientBalanceException(
                    "Saldo insuficiente en cuenta " + sourceAccount.getAccountNumber() +
                    ". Saldo disponible: " + sourceAccount.getBalance() +
                    ". Monto requerido: " + request.getAmountWithFee());
        }

        BigDecimal amount = request.getAmountWithFee();
        sourceAccount.debit(amount);
        targetAccount.credit(amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = Transfer.create(
                UUID.randomUUID().toString(),
                sourceAccount.getAccountNumber(),
                targetAccount.getAccountNumber(),
                amount,
                channel,
                request.hasExternalReference() ? request.getExternalReference() : null
        );

        idempotencyRepository.save(idempotencyKey, transfer);

        auditEventPublisher.publishTransferExecuted(transfer, sourceAccount, targetAccount);

        logger.info("Transferencia completada exitosamente: {}", transfer.getId());
        return mapToResponse(transfer);
    }

    private TransferResponse mapToResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getSourceAccountNumber(),
                transfer.getTargetAccountNumber(),
                transfer.getAmount(),
                transfer.getStatus(),
                transfer.getExecutedAt() != null ? 
                        transfer.getExecutedAt().toString() : LocalDateTime.now().toString()
        );
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java ===
package com.bank.accounts.infrastructure.audit;

import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(AuditEventPublisher.class);

    public void publishTransferExecuted(Transfer transfer, Account sourceAccount, Account targetAccount) {
        logger.info("Publicando evento de transferencia ejecutada: transferId={}, source={}, target={}, amount={}",
                transfer.getId(), transfer.getSourceAccountNumber(), 
                transfer.getTargetAccountNumber(), transfer.getAmount());

        AuditEvent event = AuditEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("TRANSFER_EXECUTED")
                .timestamp(LocalDateTime.now())
                .payload(buildTransferPayload(transfer, sourceAccount, targetAccount))
                .build();

        persistAuditEvent(event);
        notifyExternalSystems(event);
    }

    public void publishTransferFailed(Transfer transfer, String failureReason) {
        logger.warn("Publicando evento de transferencia fallida: transferId={}, reason={}",
                transfer.getId(), failureReason);

        AuditEvent event = AuditEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("TRANSFER_FAILED")
                .timestamp(LocalDateTime.now())
                .payload(buildFailurePayload(transfer, failureReason))
                .build();

        persistAuditEvent(event);
    }

    public void publishIdempotencyCheck(String idempotencyKey, boolean wasDuplicated) {
        logger.debug("Verificación de idempotencia: key={}, duplicated={}", idempotencyKey, wasDuplicated);

        AuditEvent event = AuditEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("IDEMPOTENCY_CHECK")
                .timestamp(LocalDateTime.now())
                .payload(buildIdempotencyPayload(idempotencyKey, wasDuplicated))
                .build();

        persistAuditEvent(event);
    }

    private Map<String, Object> buildTransferPayload(Transfer transfer, 
            Account sourceAccount, Account targetAccount) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("transferId", transfer.getId());
        payload.put("sourceAccountNumber", transfer.getSourceAccountNumber());
        payload.put("sourceAccountType", sourceAccount.getAccountType());
        payload.put("targetAccountNumber", transfer.getTargetAccountNumber());
        payload.put("targetAccountType", targetAccount.getAccountType());
        payload.put("amount", transfer.getAmount().toString());
        payload.put("channel", transfer.getChannel());
        payload.put("externalReference", transfer.getExternalReference());
        payload.put("sourceBalanceAfter", sourceAccount.getBalance().toString());
        payload.put("targetBalanceAfter", targetAccount.getBalance().toString());
        return payload;
    }

    private Map<String, Object> buildFailurePayload(Transfer transfer, String failureReason) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("transferId", transfer.getId());
        payload.put("sourceAccountNumber", transfer.getSourceAccountNumber());
        payload.put("targetAccountNumber", transfer.getTargetAccountNumber());
        payload.put("amount", transfer.getAmount().toString());
        payload.put("failureReason", failureReason);
        payload.put("channel", transfer.getChannel());
        return payload;
    }

    private Map<String, Object> buildIdempotencyPayload(String idempotencyKey, boolean wasDuplicated) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("idempotencyKey", idempotencyKey);
        payload.put("wasDuplicated", wasDuplicated);
        return payload;
    }

    private void persistAuditEvent(AuditEvent event) {
        logger.debug("Persisting audit event: type={}, eventId={}", 
                event.getEventType(), event.getEventId());
    }

    private void notifyExternalSystems(AuditEvent event) {
        logger.debug("Notificando sistemas externos sobre evento: type={}", event.getEventType());
    }

    public static class AuditEvent {
        private final String eventId;
        private final String eventType;
        private final LocalDateTime timestamp;
        private final Map<String, Object> payload;

        private AuditEvent(Builder builder) {
            this.eventId = builder.eventId;
            this.eventType = builder.eventType;
            this.timestamp = builder.timestamp;
            this.payload = builder.payload;
        }

        public static Builder builder() {
            return new Builder();
        }

        public String getEventId() {
            return eventId;
        }

        public String getEventType() {
            return eventType;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public Map<String, Object> getPayload() {
            return payload;
        }

        public static class Builder {
            private String eventId;
            private String eventType;
            private LocalDateTime timestamp;
            private Map<String, Object> payload;

            public Builder eventId(String eventId) {
                this.eventId = eventId;
                return this;
            }

            public Builder eventType(String eventType) {
                this.eventType = eventType;
                return this;
            }

            public Builder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder payload(Map<String, Object> payload) {
                this.payload = payload;
                return this;
            }

            public AuditEvent build() {
                return new AuditEvent(this);
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/exception/GlobalExceptionHandler.java ===
package com.bank.accounts.infrastructure.exception;

import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(
            AccountNotFoundException ex, WebRequest request) {
        logger.warn("Cuenta no encontrada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("NOT_FOUND")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(
            InsufficientBalanceException ex, WebRequest request) {
        logger.warn("Saldo insuficiente: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("INSUFFICIENT_BALANCE")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DuplicateAccountException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateAccountException(
            DuplicateAccountException ex, WebRequest request) {
        logger.warn("Cuenta duplicada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("CONFLICT")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento inválido: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("BAD_REQUEST")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());
        
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
                fieldErrors.put(error.getField(), error.getDefaultMessage()));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("VALIDATION_ERROR")
                .message("Error en la validación de los datos de entrada")
                .path(request.getDescription(false).replace("uri=", ""))
                .fieldErrors(fieldErrors)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("INTERNAL_SERVER_ERROR")
                .message("Error interno del servidor. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;

        private ErrorResponse(Builder builder) {
            this.timestamp = builder.timestamp;
            this.status = builder.status;
            this.error = builder.error;
            this.message = builder.message;
            this.path = builder.path;
        }

        public static Builder builder() {
            return new Builder();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public static class Builder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;

            public Builder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder status(int status) {
                this.status = status;
                return this;
            }

            public Builder error(String error) {
                this.error = error;
                return this;
            }

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Builder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                return new ErrorResponse(this);
            }
        }
    }

    public static class ValidationErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;
        private final Map<String, String> fieldErrors;

        private ValidationErrorResponse(Builder builder) {
            this.timestamp = builder.timestamp;
            this.status = builder.status;
            this.error = builder.error;
            this.message = builder.message;
            this.path = builder.path;
            this.fieldErrors = builder.fieldErrors;
        }

        public static Builder builder() {
            return new Builder();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public Map<String, String> getFieldErrors() {
            return fieldErrors;
        }

        public static class Builder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;
            private Map<String, String> fieldErrors;

            public Builder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder status(int status) {
                this.status = status;
                return this;
            }

            public Builder error(String error) {
                this.error = error;
                return this;
            }

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Builder path(String path) {
                this.path = path;
                return this;
            }

            public Builder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }

            public ValidationErrorResponse build() {
                return new ValidationErrorResponse(this);
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyKeyGenerator.java ===
package com.bank.accounts.infrastructure.idempotency;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * Generador de claves de idempotencia para operaciones bancarias.
 * Crea claves únicas basadas en el número de operación y el canal de solicitud.
 * Formato: IDEMPOTENCY_{canal}_{hash}_{timestamp}
 */
public class IdempotencyKeyGenerator {

    private static final String PREFIX = "IDEMPOTENCY";
    private static final String SEPARATOR = "_";
    private static final int MAX_OPERATION_NUMBER_LENGTH = 50;
    private static final int MAX_CHANNEL_LENGTH = 30;
    private static final Pattern VALID_CHANNEL_PATTERN = Pattern.compile("^[A-Z0-9_]{3,30}$");
    private static final Pattern VALID_OPERATION_PATTERN = Pattern.compile("^[A-Z0-9\-]{1,50}$");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final MessageDigest sha256Digest;

    public IdempotencyKeyGenerator() {
        try {
            this.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 no disponible en el entorno", e);
        }
    }

    /**
     * Genera una clave de idempotencia única para la operación.
     * 
     * @param operationNumber Número único de operación proporcionado por el cliente
     * @param canal Canal por el cual se realiza la solicitud (ej: API, WEB, MOBILE, ATM)
     * @return Clave de idempotencia formateada
     * @throws IllegalArgumentException si los parámetros no cumplen validación
     */
    public String generateKey(String operationNumber, String canal) {
        validarParametros(operationNumber, canal);
        
        String normalizedChannel = canal.toUpperCase().trim();
        String normalizedOperation = operationNumber.trim();
        
        String hashInput = normalizedChannel + SEPARATOR + normalizedOperation;
        String hash = calcularHash(hashInput);
        
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        
        return String.format("%s%s%s%s%s", 
            PREFIX, 
            SEPARATOR, 
            normalizedChannel, 
            SEPARATOR, 
            hash.substring(0, 16) + timestamp);
    }

    /**
     * Valida que una clave de idempotencia tenga el formato correcto.
     * 
     * @param key Clave a validar
     * @return true si la clave es válida
     */
    public boolean isValidKeyFormat(String key) {
        if (key == null || key.isBlank()) {
            return false;
        }
        
        String[] partes = key.split(SEPARATOR);
        if (partes.length < 4) {
            return false;
        }
        
        if (!PREFIX.equals(partes[0])) {
            return false;
        }
        
        String canal = partes[1];
        if (!VALID_CHANNEL_PATTERN.matcher(canal).matches()) {
            return false;
        }
        
        String resto = partes[2];
        if (resto.length() < 16) {
            return false;
        }
        
        return true;
    }

    /**
     * Extrae el número de operación de una clave de idempotencia.
     * Nota: Esto es una aproximación ya que el hash no es reversible.
     * 
     * @param key Clave de idempotencia
     * @return El canal extraído de la clave
     */
    public String extractChannel(String key) {
        if (!isValidKeyFormat(key)) {
            throw new IllegalArgumentException("Clave de idempotencia inválida");
        }
        
        String[] partes = key.split(SEPARATOR);
        return partes[1];
    }

    /**
     * Extrae el timestamp de la clave de idempotencia.
     * 
     * @param key Clave de idempotencia
     * @return Timestamp en formato yyyyMMddHHmmss
     */
    public String extractTimestamp(String key) {
        if (!isValidKeyFormat(key)) {
            throw new IllegalArgumentException("Clave de idempotencia inválida");
        }
        
        String[] partes = key.split(SEPARATOR);
        String hashYTimestamp = partes[2];
        
        if (hashYTimestamp.length() < 16) {
            throw new IllegalArgumentException("Clave de idempotencia malformada");
        }
        
        return hashYTimestamp.substring(16);
    }

    /**
     * Verifica si una clave de idempotencia ha expirado.
     * Las claves expiran después de 24 horas.
     * 
     * @param key Clave de idempotencia
     * @return true si la clave ha expirado
     */
    public boolean isExpired(String key) {
        try {
            String timestampStr = extractTimestamp(key);
            LocalDateTime claveTime = LocalDateTime.parse(timestampStr, TIMESTAMP_FORMATTER);
            LocalDateTime now = LocalDateTime.now();
            
            return claveTime.plusHours(24).isBefore(now);
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Valida los parámetros de entrada para la generación de claves.
     */
    private void validarParametros(String operationNumber, String canal) {
        if (operationNumber == null || operationNumber.isBlank()) {
            throw new IllegalArgumentException("El número de operación no puede estar vacío");
        }
        
        if (operationNumber.length() > MAX_OPERATION_NUMBER_LENGTH) {
            throw new IllegalArgumentException(
                String.format("El número de operación excede el máximo de %d caracteres", MAX_OPERATION_NUMBER_LENGTH));
        }
        
        if (!VALID_OPERATION_PATTERN.matcher(operationNumber).matches()) {
            throw new IllegalArgumentException(
                "El número de operación contiene caracteres inválidos. Solo se permiten letras, números y guiones");
        }
        
        if (canal == null || canal.isBlank()) {
            throw new IllegalArgumentException("El canal no puede estar vacío");
        }
        
        if (canal.length() > MAX_CHANNEL_LENGTH) {
            throw new IllegalArgumentException(
                String.format("El canal excede el máximo de %d caracteres", MAX_CHANNEL_LENGTH));
        }
    }

    /**
     * Calcula el hash SHA-256 de la entrada.
     */
    private String calcularHash(String entrada) {
        byte[] hashBytes = sha256Digest.digest(entrada.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
    }

    /**
     * Genera una clave de idempotencia para una transferencia.
     * Método de conveniencia que incluye información adicional.
     * 
     * @param operationNumber Número de operación
     * @param canal Canal de la solicitud
     * @param accountId ID de la cuenta origen (opcional, para mayor unicidad)
     * @return Clave de idempotencia
     */
    public String generateTransferKey(String operationNumber, String canal, String accountId) {
        String enrichedOperation = operationNumber;
        
        if (accountId != null && !accountId.isBlank()) {
            enrichedOperation = accountId + SEPARATOR + operationNumber;
        }
        
        return generateKey(enrichedOperation, canal);
    }

    /**
     * Normaliza el número de operación para garantizar consistencia.
     * Elimina espacios en blanco y convierte a mayúsculas.
     * 
     * @param operationNumber Número de operación sin normalizar
     * @return Número de operación normalizado
     */
    public String normalizeOperationNumber(String operationNumber) {
        if (operationNumber == null) {
            return null;
        }
        return operationNumber.trim().toUpperCase();
    }

    /**
     * Normaliza el canal para garantizar consistencia.
     * 
     * @param canal Canal sin normalizar
     * @return Canal normalizado en mayúsculas
     */
    public String normalizeChannel(String canal) {
        if (canal == null) {
            return null;
        }
        return canal.trim().toUpperCase();
    }
}

// === ARCHIVO: src/test/java/com/bank/accounts/api/AccountControllerTest.java ===
package com.bank.accounts.api;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.application.AccountService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountService accountService;

    private Account testAccount;
    private AccountResponse testAccountResponse;
    private AccountRequest testAccountRequest;

    @BeforeEach
    void setUp() {
        testAccount = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("1234567890")
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .active(true)
                .build();

        testAccountResponse = new AccountResponse(
                testAccount.getId(),
                testAccount.getAccountNumber(),
                testAccount.getBalance(),
                testAccount.getCurrency(),
                testAccount.isActive(),
                testAccount.getCreatedAt()
        );

        testAccountRequest = new AccountRequest(new BigDecimal("500.00"), "USD");
    }

    @Test
    @DisplayName("Crear cuenta - éxito")
    void createAccount_Success() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenReturn(testAccountResponse);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAccountRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testAccount.getId().toString()))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.balance").value(1000.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    @DisplayName("Crear cuenta - número duplicado")
    void createAccount_DuplicateNumber() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenThrow(new DuplicateAccountException("Account number already exists"));

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAccountRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Consultar cuenta por ID - éxito")
    void getAccountById_Success() throws Exception {
        UUID accountId = testAccount.getId();
        when(accountService.getAccountById(accountId)).thenReturn(testAccountResponse);

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId.toString()))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"));
    }

    @Test
    @DisplayName("Consultar cuenta por ID - no encontrada")
    void getAccountById_NotFound() throws Exception {
        UUID accountId = UUID.randomUUID();
        when(accountService.getAccountById(accountId))
                .thenThrow(new AccountNotFoundException("Account not found"));

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Listar todas las cuentas")
    void getAllAccounts_Success() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(List.of(testAccountResponse));

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].accountNumber").value("1234567890"));
    }
}

// === ARCHIVO: src/test/java/com/bank/accounts/api/TransferControllerTest.java ===
package com.bank.accounts.api;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.application.TransferService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransferService transferService;

    private TransferRequest transferRequest;
    private TransferResponse transferResponse;
    private UUID fromAccountId;
    private UUID toAccountId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        fromAccountId = UUID.randomUUID();
        toAccountId = UUID.randomUUID();
        idempotencyKey = "OP-20240115-001-CHANNEL_A";

        transferRequest = new TransferRequest(
                fromAccountId,
                toAccountId,
                new BigDecimal("100.00"),
                idempotencyKey
        );

        transferResponse = new TransferResponse(
                UUID.randomUUID(),
                fromAccountId,
                toAccountId,
                new BigDecimal("100.00"),
                new BigDecimal("2.50"),
                "COMPLETED",
                Instant.now(),
                idempotencyKey
        );
    }

    @Test
    @DisplayName("Realizar transferencia - éxito")
    void transfer_Success() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class)))
                .thenReturn(transferResponse);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fromAccountId").value(fromAccountId.toString()))
                .andExpect(jsonPath("$.toAccountId").value(toAccountId.toString()))
                .andExpect(jsonPath("$.amount").value(100.00))
                .andExpect(jsonPath("$.fee").value(2.50))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @DisplayName("Realizar transferencia - idempotencia con clave repetida")
    void transfer_IdempotencyKeyReused() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class)))
                .thenReturn(transferResponse);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());

        verify(transferService, times(2)).executeTransfer(any(TransferRequest.class));
    }

    @Test
    @DisplayName("Realizar transferencia - cuenta origen no encontrada")
    void transfer_FromAccountNotFound() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class)))
                .thenThrow(new AccountNotFoundException("Source account not found"));

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Realizar transferencia - saldo insuficiente")
    void transfer_InsufficientBalance() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class)))
                .thenThrow(new InsufficientBalanceException("Insufficient balance"));

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Realizar transferencia - misma cuenta origen y destino")
    void transfer_SameAccount() throws Exception {
        TransferRequest sameAccountRequest = new TransferRequest(
                fromAccountId,
                fromAccountId,
                new BigDecimal("100.00"),
                idempotencyKey
        );

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(sameAccountRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Realizar transferencia - monto inválido")
    void transfer_InvalidAmount() throws Exception {
        TransferRequest invalidAmountRequest = new TransferRequest(
                fromAccountId,
                toAccountId,
                new BigDecimal("-50.00"),
                idempotencyKey
        );

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(invalidAmountRequest)))
                .andExpect(status().isBadRequest());
    }
}

// === ARCHIVO: src/test/java/com/bank/accounts/application/AccountServiceTest.java ===
package com.bank.accounts.application;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private AccountRequest createAccountRequest;
    private UUID accountId;

    @BeforeEach
    void setUp() {
        accountId = UUID.randomUUID();
        testAccount = Account.builder()
                .id(accountId)
                .accountNumber("9876543210")
                .balance(new BigDecimal("2500.00"))
                .currency("USD")
                .active(true)
                .createdAt(Instant.now())
                .build();

        createAccountRequest = new AccountRequest(new BigDecimal("1000.00"), "USD");
    }

    @Test
    @DisplayName("Crear cuenta - éxito")
    void createAccount_Success() {
        when(accountRepository.existsByAccountNumber(anyString())).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        AccountResponse result = accountService.createAccount(createAccountRequest);

        assertNotNull(result);
        assertEquals(accountId, result.id());
        assertEquals("9876543210", result.accountNumber());
        assertEquals(new BigDecimal("2500.00"), result.balance());
        assertEquals("USD", result.currency());
        assertTrue(result.isActive());
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("Crear cuenta - número duplicado")
    void createAccount_DuplicateNumber() {
        when(accountRepository.existsByAccountNumber("1234567890")).thenReturn(true);

        assertThrows(DuplicateAccountException.class, () -> {
            accountService.createAccount(new AccountRequest(new BigDecimal("500.00"), "USD"));
        });

        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Obtener cuenta por ID - éxito")
    void getAccountById_Success() {
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));

        AccountResponse result = accountService.getAccountById(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.id());
        assertEquals("9876543210", result.accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por ID - no encontrada")
    void getAccountById_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(accountRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountById(randomId);
        });
    }

    @Test
    @DisplayName("Obtener todas las cuentas")
    void getAllAccounts_Success() {
        when(accountRepository.findAll()).thenReturn(List.of(testAccount));

        List<AccountResponse> result = accountService.getAllAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("9876543210", result.get(0).accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por número - éxito")
    void getAccountByNumber_Success() {
        when(accountRepository.findByAccountNumber("9876543210"))
                .thenReturn(Optional.of(testAccount));

        AccountResponse result = accountService.getAccountByNumber("9876543210");

        assertNotNull(result);
        assertEquals("9876543210", result.accountNumber());
    }

    @Test
    @DisplayName("Obtener cuenta por número - no encontrada")
    void getAccountByNumber_NotFound() {
        when(accountRepository.findByAccountNumber("0000000000")).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountByNumber("0000000000");
        });
    }

    @Test
    @DisplayName("Desactivar cuenta - éxito")
    void deactivateAccount_Success() {
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        accountService.deactivateAccount(accountId);

        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("Desactivar cuenta - no encontrada")
    void deactivateAccount_NotFound() {
        UUID randomId = UUID.randomUUID();
        when(accountRepository.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.deactivateAccount(randomId);
        });
    }
}

// === ARCHIVO: src/test/java/com/bank/accounts/application/TransferServiceTest.java ===
package com.bank.accounts.application;

import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransferService - Pruebas Unitarias")
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IdempotencyRepository idempotencyRepository;

    @InjectMocks
    private TransferService transferService;

    private Account cuentaOrigen;
    private Account cuentaDestino;

    @BeforeEach
    void setUp() {
        cuentaOrigen = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("1234567890")
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        cuentaDestino = Account.builder()
                .id(UUID.randomUUID())
                .accountNumber("0987654321")
                .balance(new BigDecimal("500.00"))
                .currency("USD")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("Escenarios de Transferencia Exitosa")
    class TransferenciaExitosa {

        @Test
        @DisplayName("Debería ejecutar transferencia cuando las cuentas existen y tienen saldo suficiente")
        void deberiaEjecutarTransferencia_CuandoCuentasExisten_Y tieneSaldoSuficiente() {
            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(any())).thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("200.00"),
                    Optional.empty()
            );

            assertNotNull(resultado);
            assertEquals(cuentaOrigen.getId(), resultado.getOriginAccountId());
            assertEquals(cuentaDestino.getId(), resultado.getDestinationAccountId());
            assertEquals(new BigDecimal("200.00"), resultado.getAmount());

            verify(accountRepository, times(2)).save(any(Account.class));
        }

        @Test
        @DisplayName("Debería actualizar saldos correctamente después de la transferencia")
        void deberiaActualizarSaldos_Correctamente() {
            BigDecimal montoTransferencia = new BigDecimal("300.00");

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(any())).thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    montoTransferencia,
                    Optional.empty()
            );

            ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
            verify(accountRepository, times(2)).save(captor.capture());

            Account cuentaOrigenActualizada = captor.getAllValues().get(0);
            Account cuentaDestinoActualizada = captor.getAllValues().get(1);

            assertEquals(new BigDecimal("700.00"), cuentaOrigenActualizada.getBalance());
            assertEquals(new BigDecimal("800.00"), cuentaDestinoActualizada.getBalance());
        }
    }

    @Nested
    @DisplayName("Escenarios de Error - Cuenta No Encontrada")
    class ErroresCuentaNoEncontrada {

        @Test
        @DisplayName("Debería lanzar AccountNotFoundException cuando la cuenta de origen no existe")
        void deberiaLanzarExcepcion_CuandoCuentaOrigenNoExiste() {
            UUID idInexistente = UUID.randomUUID();

            when(accountRepository.findById(idInexistente))
                    .thenReturn(Optional.empty());
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(AccountNotFoundException.class, () ->
                    transferService.executeTransfer(
                            idInexistente,
                            cuentaDestino.getId(),
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }

        @Test
        @DisplayName("Debería lanzar AccountNotFoundException cuando la cuenta de destino no existe")
        void deberiaLanzarExcepcion_CuandoCuentaDestinoNoExiste() {
            UUID idInexistente = UUID.randomUUID();

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(idInexistente))
                    .thenReturn(Optional.empty());

            assertThrows(AccountNotFoundException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            idInexistente,
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }
    }

    @Nested
    @DisplayName("Escenarios de Error - Saldo Insuficiente")
    class ErroresSaldoInsuficiente {

        @Test
        @DisplayName("Debería lanzar InsufficientBalanceException cuando el saldo es menor al monto")
        void deberiaLanzarExcepcion_CuandoSaldoEsMenorAlMonto() {
            BigDecimal montoMayorAlSaldo = new BigDecimal("1500.00");

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(InsufficientBalanceException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            montoMayorAlSaldo,
                            Optional.empty()
                    )
            );

            verify(accountRepository, never()).save(any(Account.class));
        }

        @Test
        @DisplayName("Debería lanzar InsufficientBalanceException cuando el saldo es exactamente igual al monto")
        void deberiaLanzarExcepcion_CuandoSaldoEsIgualAlMonto() {
            BigDecimal montoIgualAlSaldo = new BigDecimal("1000.00");

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(InsufficientBalanceException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            montoIgualAlSaldo,
                            Optional.empty()
                    )
            );
        }
    }

    @Nested
    @DisplayName("Escenarios de Idempotencia")
    class EscenariosIdempotencia {

        @Test
        @DisplayName("Debería retornar transferencia existente cuando la clave de idempotencia ya existe")
        void deberiaRetornarTransferenciaExistente_CuandoClaveIdempotenciaYaExiste() {
            String claveIdempotencia = "TRANSFER-12345-CHANNEL";
            Transfer transferenciaExistente = Transfer.builder()
                    .id(UUID.randomUUID())
                    .originAccountId(cuentaOrigen.getId())
                    .destinationAccountId(cuentaDestino.getId())
                    .amount(new BigDecimal("200.00"))
                    .idempotencyKey(claveIdempotencia)
                    .createdAt(LocalDateTime.now().minusHours(1))
                    .build();

            when(idempotencyRepository.findByKey(claveIdempotencia))
                    .thenReturn(Optional.of(transferenciaExistente));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("200.00"),
                    Optional.of(claveIdempotencia)
            );

            assertNotNull(resultado);
            assertEquals(transferenciaExistente.getId(), resultado.getId());
            assertEquals(transferenciaExistente.getCreatedAt(), resultado.getCreatedAt());

            verify(accountRepository, never()).findById(any());
            verify(accountRepository, never()).save(any(Account.class));
        }

        @Test
        @DisplayName("Debería crear nueva transferencia cuando la clave de idempotencia no existe")
        void deberiaCrearNuevaTransferencia_CuandoClaveIdempotenciaNoExiste() {
            String nuevaClaveIdempotencia = "TRANSFER-NUEVO-123";

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(nuevaClaveIdempotencia))
                    .thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("150.00"),
                    Optional.of(nuevaClaveIdempotencia)
            );

            assertNotNull(resultado);
            assertEquals(nuevaClaveIdempotencia, resultado.getIdempotencyKey());
            verify(idempotencyRepository).save(any());
        }

        @Test
        @DisplayName("Debería usar clave autogenerada cuando no se provee idempotencia")
        void deberiaUsarClaveAutogenerada_CuandoNoSeProveeIdempotencia() {
            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaOrigen));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));
            when(idempotencyRepository.findByKey(any())).thenReturn(Optional.empty());
            when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

            Transfer resultado = transferService.executeTransfer(
                    cuentaOrigen.getId(),
                    cuentaDestino.getId(),
                    new BigDecimal("100.00"),
                    Optional.empty()
            );

            assertNotNull(resultado);
            assertNotNull(resultado.getIdempotencyKey());
            assertTrue(resultado.getIdempotencyKey().startsWith("TRANSFER-"));
        }
    }

    @Nested
    @DisplayName("Escenarios de Validación")
    class EscenariosValidacion {

        @Test
        @DisplayName("Debería lanzar excepción cuando se intenta transferir a la misma cuenta")
        void deberiaLanzarExcepcion_CuandoSeIntentaTransferirALaMismaCuenta() {
            assertThrows(IllegalArgumentException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaOrigen.getId(),
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando el monto es menor o igual a cero")
        void deberiaLanzarExcepcion_CuandoMontoEsMenorOIgualACero() {
            assertThrows(IllegalArgumentException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            BigDecimal.ZERO,
                            Optional.empty()
                    )
            );

            assertThrows(IllegalArgumentException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            new BigDecimal("-50.00"),
                            Optional.empty()
                    )
            );
        }

        @Test
        @DisplayName("Debería lanzar excepción cuando la cuenta de origen está inactiva")
        void deberiaLanzarExcepcion_CuandoCuentaOrigenEstaInactiva() {
            Account cuentaInactiva = Account.builder()
                    .id(cuentaOrigen.getId())
                    .accountNumber(cuentaOrigen.getAccountNumber())
                    .balance(new BigDecimal("1000.00"))
                    .currency("USD")
                    .active(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            when(accountRepository.findById(cuentaOrigen.getId()))
                    .thenReturn(Optional.of(cuentaInactiva));
            when(accountRepository.findById(cuentaDestino.getId()))
                    .thenReturn(Optional.of(cuentaDestino));

            assertThrows(IllegalStateException.class, () ->
                    transferService.executeTransfer(
                            cuentaOrigen.getId(),
                            cuentaDestino.getId(),
                            new BigDecimal("100.00"),
                            Optional.empty()
                    )
            );
        }
    }
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>accounts</artifactId>
    <version>1.0.0</version>
    <name>accounts</name>
    <description>Bank Accounts Management System</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>3.3.4</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>3.3.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <version>3.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.16</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/AccountRequest.java ===
package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccountRequest(
    String accountNumber,
    String accountType,
    String currency,
    BigDecimal initialBalance,
    String ownerDocument,
    String ownerName
) {
    private static final List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "MXN", "COP");
    private static final BigDecimal MIN_INITIAL_BALANCE = BigDecimal.ZERO;
    private static final BigDecimal MAX_INITIAL_BALANCE = new BigDecimal("1000000");

    public BigDecimal getInitialBalanceOrZero() {
        return initialBalance != null ? initialBalance : BigDecimal.ZERO;
    }

    public boolean isInitialBalanceValid() {
        BigDecimal balance = getInitialBalanceOrZero();
        return balance.compareTo(MIN_INITIAL_BALANCE) >= 0 
            && balance.compareTo(MAX_INITIAL_BALANCE) <= 0;
    }

    public boolean isCurrencySupported() {
        return currency != null && SUPPORTED_CURRENCIES.contains(currency.toUpperCase());
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/TransferRequest.java ===
package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(
    UUID sourceAccountId,
    UUID targetAccountId,
    BigDecimal amount,
    String channel,
    String externalReference,
    String description
) {
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("0.01");
    private static final BigDecimal FEE_PERCENTAGE = new BigDecimal("0.005");
    private static final BigDecimal MAX_FEE = new BigDecimal("50.00");

    public boolean isValidAmount() {
        return amount != null && amount.compareTo(MIN_AMOUNT) >= 0;
    }

    public boolean isSameAccount() {
        return sourceAccountId != null && sourceAccountId.equals(targetAccountId);
    }

    public boolean hasExternalReference() {
        return externalReference != null && !externalReference.isBlank();
    }

    public String generateOperationKey() {
        return sourceAccountId.toString() + "-" + targetAccountId.toString() + "-" + System.currentTimeMillis();
    }

    public BigDecimal getAmountWithFee() {
        return amount.add(calculateFee());
    }

    private BigDecimal calculateFee() {
        BigDecimal fee = amount.multiply(FEE_PERCENTAGE);
        return fee.compareTo(MAX_FEE) > 0 ? MAX_FEE : fee;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/model/Account.java ===
package com.bank.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Account {

    public enum AccountStatus {
        ACTIVE, INACTIVE, BLOCKED
    }

    private UUID id;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private AccountStatus status;
    private String ownerDocument;
    private String ownerName;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastTransactionAt;
    private int dailyTransactionCount;
    private BigDecimal dailyTotalAmount;
    private String customerId;

    public Account() {
    }

    public Account(UUID id, String accountNumber, String accountType, BigDecimal balance,
                   String currency, AccountStatus status, String ownerDocument, String ownerName,
                   LocalDate createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.ownerDocument = ownerDocument;
        this.ownerName = ownerName;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor a cero");
        }
        this.balance = this.balance.add(amount);
        this.lastTransactionAt = LocalDateTime.now();
        this.dailyTransactionCount++;
        this.dailyTotalAmount = this.dailyTotalAmount.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor a cero");
        }
        if (!hasSufficientBalance(amount)) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.balance = this.balance.subtract(amount);
        this.lastTransactionAt = LocalDateTime.now();
        this.dailyTransactionCount++;
        this.dailyTotalAmount = this.dailyTotalAmount.add(amount);
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

    public boolean isActive() {
        return this.status == AccountStatus.ACTIVE;
    }

    public boolean isInactive() {
        return this.status == AccountStatus.INACTIVE;
    }

    public boolean isBlocked() {
        return this.status == AccountStatus.BLOCKED;
    }

    public void activate() {
        this.status = AccountStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = AccountStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void block() {
        this.status = AccountStatus.BLOCKED;
        this.updatedAt = LocalDateTime.now();
    }

    public void resetDailyLimits() {
        this.dailyTransactionCount = 0;
        this.dailyTotalAmount = BigDecimal.ZERO;
    }

    public boolean hasReachedDailyLimit(int maxTransactions) {
        return this.dailyTransactionCount >= maxTransactions;
    }

    public boolean hasReachedDailyAmountLimit(BigDecimal maxAmount) {
        return this.dailyTotalAmount.compareTo(maxAmount) >= 0;
    }

    public String getFormattedBalance() {
        return String.format("%s %s", this.currency, this.balance.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    public String getAccountTypeDescription() {
        return switch (this.accountType) {
            case "SAVINGS" -> "Cuenta de Ahorros";
            case "CHECKING" -> "Cuenta Corriente";
            case "INVESTMENT" -> "Cuenta de Inversión";
            default -> "Cuenta " + this.accountType;
        };
    }

    public String getStatusDescription() {
        return switch (this.status) {
            case ACTIVE -> "Activa";
            case INACTIVE -> "Inactiva";
            case BLOCKED -> "Bloqueada";
        };
    }

    public long getDaysSinceCreation() {
        return this.createdAt != null ? ChronoUnit.DAYS.between(this.createdAt, LocalDate.now()) : 0;
    }

    public boolean isNewAccount() {
        return getDaysSinceCreation() < 30;
    }

    public boolean hasPositiveBalance() {
        return this.balance.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean hasZeroBalance() {
        return this.balance.compareTo(BigDecimal.ZERO) == 0;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public String getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(String ownerDocument) { this.ownerDocument = ownerDocument; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getLastTransactionAt() { return lastTransactionAt; }
    public void setLastTransactionAt(LocalDateTime lastTransactionAt) { this.lastTransactionAt = lastTransactionAt; }

    public int getDailyTransactionCount() { return dailyTransactionCount; }
    public void setDailyTransactionCount(int dailyTransactionCount) { this.dailyTransactionCount = dailyTransactionCount; }

    public BigDecimal getDailyTotalAmount() { return dailyTotalAmount; }
    public void setDailyTotalAmount(BigDecimal dailyTotalAmount) { this.dailyTotalAmount = dailyTotalAmount; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public LocalDateTime getLastModifiedAt() { return updatedAt; }
    public void setLastModifiedAt(LocalDateTime lastModifiedAt) { this.updatedAt = lastModifiedAt; }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/model/Transfer.java ===
package com.bank.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Transfer {

    public enum TransferStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, REJECTED
    }

    private UUID id;
    private UUID sourceAccountId;
    private UUID targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private TransferStatus status;
    private String channel;
    private String idempotencyKey;
    private String operationNumber;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime failedAt;
    private String failureReason;
    private int retryCount;
    private String externalReference;
    private String sourceAccountNumber;
    private String targetAccountNumber;

    private static final int MAX_RETRY_COUNT = 3;

    public Transfer() {
    }

    public Transfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount,
                    String channel, String idempotencyKey) {
        this.id = UUID.randomUUID();
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.fee = calculateFee(amount);
        this.status = TransferStatus.PENDING;
        this.channel = channel;
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = generateOperationNumber();
        this.createdAt = LocalDateTime.now();
    }

    private BigDecimal calculateFee(BigDecimal amount) {
        BigDecimal feePercentage = new BigDecimal("0.005");
        BigDecimal fee = amount.multiply(feePercentage);
        BigDecimal maxFee = new BigDecimal("50.00");
        return fee.compareTo(maxFee) > 0 ? maxFee : fee;
    }

    private String generateOperationNumber() {
        return "OP-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    public static String generateIdempotencyKey(String channel, String operationNumber) {
        return channel + "|" + operationNumber + "|" + System.currentTimeMillis();
    }

    public static Transfer create(String idempotencyKey, String sourceAccountNumber, 
                                   String targetAccountNumber, BigDecimal amount, 
                                   String channel, String externalReference) {
        Transfer transfer = new Transfer();
        transfer.id = UUID.randomUUID();
        transfer.sourceAccountNumber = sourceAccountNumber;
        transfer.targetAccountNumber = targetAccountNumber;
        transfer.amount = amount;
        transfer.fee = transfer.calculateFee(amount);
        transfer.status = TransferStatus.PENDING;
        transfer.channel = channel;
        transfer.idempotencyKey = idempotencyKey;
        transfer.operationNumber = transfer.generateOperationNumber();
        transfer.externalReference = externalReference;
        transfer.createdAt = LocalDateTime.now();
        return transfer;
    }

    public boolean isPending() { return this.status == TransferStatus.PENDING; }
    public boolean isProcessing() { return this.status == TransferStatus.PROCESSING; }
    public boolean isCompleted() { return this.status == TransferStatus.COMPLETED; }
    public boolean isFailed() { return this.status == TransferStatus.FAILED; }
    public boolean isRejected() { return this.status == TransferStatus.REJECTED; }

    public void markAsProcessing() { this.status = TransferStatus.PROCESSING; }
    public void markAsCompleted() { 
        this.status = TransferStatus.COMPLETED; 
        this.completedAt = LocalDateTime.now();
    }
    public void markAsFailed(String reason) { 
        this.status = TransferStatus.FAILED; 
        this.failedAt = LocalDateTime.now();
        this.failureReason = reason;
    }
    public void markAsRejected(String reason) {
        this.status = TransferStatus.REJECTED;
        this.failureReason = reason;
    }

    public boolean canRetry() { return this.retryCount < MAX_RETRY_COUNT; }
    public void incrementRetryCount() { this.retryCount++; }

    public boolean isIdempotent(String newIdempotencyKey) {
        return this.idempotencyKey != null && this.idempotencyKey.equals(newIdempotencyKey);
    }

    public BigDecimal getTotalAmount() { return this.amount.add(this.fee); }
    public boolean isSameAccount() { return this.sourceAccountId.equals(this.targetAccountId); }
    public boolean isValidAmount() { return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0; }
    public boolean isInterbank() { return true; }
    public boolean isHighValue() { return this.amount.compareTo(new BigDecimal("10000")) >= 0; }

    public long getProcessingTimeSeconds() {
        if (this.completedAt == null || this.createdAt == null) return 0;
        return ChronoUnit.SECONDS.between(this.createdAt, this.completedAt);
    }

    public String getStatusDescription() {
        return switch (this.status) {
            case PENDING -> "Pendiente";
            case PROCESSING -> "Procesando";
            case COMPLETED -> "Completada";
            case FAILED -> "Fallida";
            case REJECTED -> "Rechazada";
        };
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getSourceAccountId() { return sourceAccountId; }
    public void setSourceAccountId(UUID sourceAccountId) { this.sourceAccountId = sourceAccountId; }

    public UUID getTargetAccountId() { return targetAccountId; }
    public void setTargetAccountId(UUID targetAccountId) { this.targetAccountId = targetAccountId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }

    public TransferStatus getStatus() { return status; }
    public void setStatus(TransferStatus status) { this.status = status; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }

    public String getOperationNumber() { return operationNumber; }
    public void setOperationNumber(String operationNumber) { this.operationNumber = operationNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public LocalDateTime getFailedAt() { return failedAt; }
    public void setFailedAt(LocalDateTime failedAt) { this.failedAt = failedAt; }

    public String getFailureReason() { return failureReason; }
    public void setFailureReason(String failureReason) { this.failureReason = failureReason; }

    public int getRetryCount() { return retryCount; }
    public void setRetryCount(int retryCount) { this.retryCount = retryCount; }

    public String getExternalReference() { return externalReference; }
    public void setExternalReference(String externalReference) { this.externalReference = externalReference; }

    public String getSourceAccountNumber() { return sourceAccountNumber; }
    public void setSourceAccountNumber(String sourceAccountNumber) { this.sourceAccountNumber = sourceAccountNumber; }

    public String getTargetAccountNumber() { return targetAccountNumber; }
    public void setTargetAccountNumber(String targetAccountNumber) { this.targetAccountNumber = targetAccountNumber; }

    public LocalDateTime getExecutedAt() { return completedAt; }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/repository/AccountRepository.java ===
package com.bank.accounts.domain.repository;

import com.bank.accounts.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(String customerId);
    List<Account> findActiveByCustomerId(String customerId);
    List<Account> findByActive(boolean active);
    Account save(Account account);
    Account update(Account account);
    void deleteById(UUID id);
    boolean existsByAccountNumber(String accountNumber);
    boolean existsById(UUID id);
    int updateBalance(UUID accountId, BigDecimal newBalance);
    boolean blockAccount(UUID accountId);
    boolean unblockAccount(UUID accountId);
    long count();
    List<Account> findByAccountType(String accountType);
    List<Account> findByStatus(Account.AccountStatus status);
    List<Account> findByCurrency(String currency);
    List<Account> findAll();
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyKeyGenerator.java ===
package com.bank.accounts.infrastructure.idempotency;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class IdempotencyKeyGenerator {

    private static final String PREFIX = "IDEMP";
    private static final String SEPARATOR = "|";
    private static final int MAX_OPERATION_NUMBER_LENGTH = 50;
    private static final int MAX_CHANNEL_LENGTH = 20;
    private static final Pattern VALID_CHANNEL_PATTERN = Pattern.compile("^[A-Z0-9_]{3,20}$");
    private static final Pattern VALID_OPERATION_PATTERN = Pattern.compile("^[A-Z0-9\\-]{1,50}$");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final MessageDigest sha256Digest;

    public IdempotencyKeyGenerator() {
        try {
            this.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public String generateKey(String operationNumber, String canal) {
        validarParametros(operationNumber, canal);
        
        String normalizedOp = normalizeOperationNumber(operationNumber);
        String normalizedChannel = normalizeChannel(canal);
        
        String rawKey = PREFIX + SEPARATOR + normalizedChannel + SEPARATOR + 
                        normalizedOp + SEPARATOR + LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        
        return calcularHash(rawKey);
    }

    public String generate(String operationNumber, String canal) {
        return generateKey(operationNumber, canal);
    }

    public boolean isValidKeyFormat(String key) {
        if (key == null || key.isBlank()) return false;
        return key.startsWith(PREFIX) && key.length() == 71;
    }

    public String extractChannel(String key) {
        if (!isValidKeyFormat(key)) return null;
        String[] parts = key.split("\\" + SEPARATOR);
        return parts.length > 1 ? parts[1] : null;
    }

    public String extractTimestamp(String key) {
        if (!isValidKeyFormat(key)) return null;
        String[] parts = key.split("\\" + SEPARATOR);
        return parts.length > 3 ? parts[3] : null;
    }

    public boolean isExpired(String key) {
        String timestamp = extractTimestamp(key);
        if (timestamp == null) return true;
        try {
            LocalDateTime keyTime = LocalDateTime.parse(timestamp, TIMESTAMP_FORMATTER);
            return keyTime.plusHours(24).isBefore(LocalDateTime.now());
        } catch (Exception e) {
            return true;
        }
    }

    private void validarParametros(String operationNumber, String canal) {
        if (operationNumber == null || operationNumber.isBlank()) {
            throw new IllegalArgumentException("El numero de operacion no puede ser nulo o vacio");
        }
        if (canal == null || canal.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacio");
        }
        if (operationNumber.length() > MAX_OPERATION_NUMBER_LENGTH) {
            throw new IllegalArgumentException("El numero de operacion excede el limite de " + MAX_OPERATION_NUMBER_LENGTH + " caracteres");
        }
        if (canal.length() > MAX_CHANNEL_LENGTH) {
            throw new IllegalArgumentException("El canal excede el limite de " + MAX_CHANNEL_LENGTH + " caracteres");
        }
    }

    private String calcularHash(String entrada) {
        byte[] hash = sha256Digest.digest(entrada.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return PREFIX + SEPARATOR + hexString.toString();
    }

    public String generateTransferKey(String operationNumber, String canal, String accountId) {
        validarParametros(operationNumber, canal);
        String baseKey = generateKey(operationNumber, canal);
        return baseKey + SEPARATOR + accountId;
    }

    public String normalizeOperationNumber(String operationNumber) {
        return operationNumber != null ? operationNumber.trim().toUpperCase() : "";
    }

    public String normalizeChannel(String canal) {
        return canal != null ? canal.trim().toUpperCase() : "";
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyRepository.java ===
package com.bank.accounts.infrastructure.idempotency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IdempotencyRepository {
    boolean existsByOperationKey(String operationKey);
    void save(IdempotencyRecord record);
    void save(String operationKey, com.bank.accounts.domain.model.Transfer transfer);
    Optional<IdempotencyRecord> findByOperationKey(String operationKey);
    default Optional<com.bank.accounts.domain.model.Transfer> findByKey(String operationKey) {
        return findByOperationKey(operationKey).map(IdempotencyRecord::toTransfer);
    }
    void deleteByOperationKey(String operationKey);
    void deleteExpiredRecords(LocalDateTime cutoffDate);
    long countByChannelAndCreatedAfter(String channel, LocalDateTime after);
    List<IdempotencyRecord> findByChannelAndCreatedBetween(String channel, LocalDateTime start, LocalDateTime end);

    record IdempotencyRecord(
        String operationKey,
        String channel,
        String operationNumber,
        LocalDateTime createdAt,
        LocalDateTime expiresAt,
        String transferData
    ) {
        public com.bank.accounts.domain.model.Transfer toTransfer() {
            return null;
        }
    }
}


// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>accounts</artifactId>
    <version>1.0.0</version>
    <name>accounts</name>
    <description>Bank Accounts Management System</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.3.4</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>3.3.4</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>3.3.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <version>3.3.4</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.16</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.17.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/test/java/com/bank/accounts/api/AccountControllerTest.java ===
package com.bank.accounts.api;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.application.AccountService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountService accountService;

    private Account testAccount;
    private AccountResponse testAccountResponse;
    private AccountRequest testAccountRequest;

    @BeforeEach
    void setUp() {
        testAccount = new Account(
                UUID.randomUUID(),
                "1234567890",
                "SAVINGS",
                new BigDecimal("1000.00"),
                "USD",
                "ACTIVE",
                "DOC123",
                "Test Owner",
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                0,
                BigDecimal.ZERO
        );

        testAccountResponse = new AccountResponse(
                testAccount.getId(),
                testAccount.getAccountNumber(),
                testAccount.getBalance(),
                testAccount.getCurrency(),
                testAccount.isActive(),
                testAccount.getCreatedAt()
        );

        testAccountRequest = new AccountRequest(new BigDecimal("500.00"), "USD");
    }

    @Test
    @DisplayName("Crear cuenta - éxito")
    void createAccount_Success() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenReturn(testAccountResponse);

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAccountRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testAccount.getId().toString()))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andExpect(jsonPath("$.balance").value(1000.00))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    @DisplayName("Crear cuenta - número duplicado")
    void createAccount_DuplicateNumber() throws Exception {
        when(accountService.createAccount(any(AccountRequest.class)))
                .thenThrow(new DuplicateAccountException("Account number already exists"));

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAccountRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Consultar cuenta por ID - éxito")
    void getAccountById_Success() throws Exception {
        Long accountId = 1L;
        when(accountService.getAccount(accountId)).thenReturn(testAccountResponse);

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testAccount.getId().toString()))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"));
    }

    @Test
    @DisplayName("Consultar cuenta por ID - no encontrada")
    void getAccountById_NotFound() throws Exception {
        Long accountId = 999L;
        when(accountService.getAccount(accountId))
                .thenThrow(new AccountNotFoundException("Account not found"));

        mockMvc.perform(get("/api/accounts/{id}", accountId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Listar todas las cuentas")
    void getAllAccounts_Success() throws Exception {
        when(accountService.getAllAccounts(eq((String) null), eq((String) null)))
                .thenReturn(List.of(testAccountResponse));

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].accountNumber").value("1234567890"));
    }
}

// === ARCHIVO: src/test/java/com/bank/accounts/api/TransferControllerTest.java ===
package com.bank.accounts.api;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.application.TransferService;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransferService transferService;

    private TransferRequest transferRequest;
    private TransferResponse transferResponse;
    private UUID fromAccountId;
    private UUID toAccountId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        fromAccountId = UUID.randomUUID();
        toAccountId = UUID.randomUUID();
        idempotencyKey = "OP-20240115-001-CHANNEL_A";

        transferRequest = new TransferRequest(
                fromAccountId,
                toAccountId,
                new BigDecimal("100.00"),
                idempotencyKey
        );

        transferResponse = new TransferResponse(
                UUID.randomUUID(),
                fromAccountId,
                toAccountId,
                new BigDecimal("100.00"),
                new BigDecimal("2.50"),
                "COMPLETED",
                Instant.now(),
                idempotencyKey
        );
    }

    @Test
    @DisplayName("Realizar transferencia - éxito")
    void transfer_Success() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenReturn(transferResponse);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fromAccountId").value(fromAccountId.toString()))
                .andExpect(jsonPath("$.toAccountId").value(toAccountId.toString()))
                .andExpect(jsonPath("$.amount").value(100.00))
                .andExpect(jsonPath("$.fee").value(2.50))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @DisplayName("Realizar transferencia - idempotencia con clave repetida")
    void transfer_IdempotencyKeyReused() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenReturn(transferResponse);

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isOk());

        verify(transferService, times(2)).executeTransfer(any(TransferRequest.class), any(String.class));
    }

    @Test
    @DisplayName("Realizar transferencia - cuenta origen no encontrada")
    void transfer_FromAccountNotFound() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenThrow(new AccountNotFoundException("Source account not found"));

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Realizar transferencia - saldo insuficiente")
    void transfer_InsufficientBalance() throws Exception {
        when(transferService.executeTransfer(any(TransferRequest.class), any(String.class)))
                .thenThrow(new InsufficientBalanceException("Insufficient balance"));

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(transferRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Realizar transferencia - misma cuenta origen y destino")
    void transfer_SameAccount() throws Exception {
        TransferRequest sameAccountRequest = new TransferRequest(
                fromAccountId,
                fromAccountId,
                new BigDecimal("100.00"),
                idempotencyKey
        );

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(sameAccountRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Realizar transferencia - monto inválido")
    void transfer_InvalidAmount() throws Exception {
        TransferRequest invalidAmountRequest = new TransferRequest(
                fromAccountId,
                toAccountId,
                new BigDecimal("-50.00"),
                idempotencyKey
        );

        mockMvc.perform(post("/api/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Idempotency-Key", idempotencyKey)
                        .content(objectMapper.writeValueAsString(invalidAmountRequest)))
                .andExpect(status().isBadRequest());
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/AccountResponse.java ===
package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponse(
    UUID id,
    String accountNumber,
    String accountType,
    BigDecimal balance,
    String currency,
    String status,
    String ownerDocument,
    String ownerName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    public boolean isInactive() {
        return "INACTIVE".equalsIgnoreCase(status);
    }

    public boolean isBlocked() {
        return "BLOCKED".equalsIgnoreCase(status);
    }

    public boolean hasPositiveBalance() {
        return balance != null && balance.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean hasSufficientBalance(BigDecimal amount) {
        return balance != null && balance.compareTo(amount) >= 0;
    }

    public String getFormattedBalance() {
        if (balance == null) return "0.00";
        return String.format("%s %s", currency, balance.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    public String getAccountTypeDescription() {
        return accountType != null ? accountType : "UNKNOWN";
    }

    public String getStatusDescription() {
        return status != null ? status : "UNKNOWN";
    }

    public long getDaysSinceCreation() {
        if (createdAt == null) return 0;
        return java.time.Duration.between(createdAt, LocalDateTime.now()).toDays();
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/repository/AccountRepository.java ===
package com.bank.accounts.domain.repository;

import com.bank.accounts.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID id);
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(String customerId);
    List<Account> findActiveByCustomerId(String customerId);
    List<Account> findByActive(boolean active);
    List<Account> findAll();
    Account save(Account account);
    Account update(Account account);
    void deleteById(UUID id);
    boolean existsByAccountNumber(String accountNumber);
    boolean existsById(UUID id);
    int updateBalance(UUID accountId, BigDecimal newBalance);
    boolean blockAccount(UUID accountId);
    boolean unblockAccount(UUID accountId);
    long count();
    List<Account> findByAccountType(String accountType);
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/persistence/AccountJpaRepository.java ===
package com.bank.accounts.infrastructure.persistence;

import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountJpaRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> findById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", accountNumber)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.ownerDocument = :customerId", Account.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<Account> findActiveByCustomerId(String customerId) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.ownerDocument = :customerId AND a.status = 'ACTIVE'", Account.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List<Account> findByActive(boolean active) {
        String status = active ? "ACTIVE" : "INACTIVE";
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.status = :status", Account.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            entityManager.persist(account);
            return account;
        } else {
            return entityManager.merge(account);
        }
    }

    @Override
    public Account update(Account account) {
        return entityManager.merge(account);
    }

    @Override
    public void deleteById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        Long count = entityManager.createQuery(
                "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber", Long.class)
                .setParameter("accountNumber", accountNumber)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsById(UUID id) {
        Account account = entityManager.find(Account.class, id);
        return account != null;
    }

    @Override
    public int updateBalance(UUID accountId, BigDecimal newBalance) {
        return entityManager.createQuery(
                "UPDATE Account a SET a.balance = :newBalance, a.updatedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
                .setParameter("newBalance", newBalance)
                .setParameter("id", accountId)
                .executeUpdate();
    }

    @Override
    public boolean blockAccount(UUID accountId) {
        int updated = entityManager.createQuery(
                "UPDATE Account a SET a.status = 'BLOCKED', a.updatedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
                .setParameter("id", accountId)
                .executeUpdate();
        return updated > 0;
    }

    @Override
    public boolean unblockAccount(UUID accountId) {
        int updated = entityManager.createQuery(
                "UPDATE Account a SET a.status = 'ACTIVE', a.updatedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
                .setParameter("id", accountId)
                .executeUpdate();
        return updated > 0;
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(a) FROM Account a", Long.class).getSingleResult();
    }

    @Override
    public List<Account> findByAccountType(String accountType) {
        return entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.accountType = :accountType", Account.class)
                .setParameter("accountType", accountType)
                .getResultList();
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/application/AccountService.java ===
package com.bank.accounts.application;

import com.bank.accounts.api.dto.AccountRequest;
import com.bank.accounts.api.dto.AccountResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.DuplicateAccountException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.persistence.AccountJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final List<String> SUPPORTED_CURRENCIES = List.of("USD", "EUR", "GBP");

    private final AccountRepository accountRepository;
    private final AccountJpaRepository jpaRepository;

    public AccountService(AccountRepository accountRepository, AccountJpaRepository jpaRepository) {
        this.accountRepository = accountRepository;
        this.jpaRepository = jpaRepository;
    }

    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByAccountNumber(request.accountNumber() != null ? request.accountNumber() : generateAccountNumber())) {
            throw new DuplicateAccountException(request.accountNumber());
        }

        Account account = new Account(
                UUID.randomUUID(),
                generateAccountNumber(),
                request.accountType() != null ? request.accountType() : "SAVINGS",
                request.getInitialBalanceOrZero(),
                request.currency() != null ? request.currency() : "USD",
                "ACTIVE",
                request.ownerDocument(),
                request.ownerName(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Account saved = accountRepository.save(account);
        return mapToResponse(saved);
    }

    public AccountResponse getAccount(Long accountId) {
        UUID uuid = UUID.randomUUID(); // Simulación - en implementación real convertir Long a UUID
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountId)));
        return mapToResponse(account);
    }

    public AccountResponse getAccountById(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId.toString()));
        return mapToResponse(account);
    }

    public AccountResponse getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(null, accountNumber));
        return mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AccountResponse> getAllAccounts(String status, String currency) {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .filter(acc -> status == null || status.equalsIgnoreCase(acc.getStatus()))
                .filter(acc -> currency == null || currency.equalsIgnoreCase(acc.getCurrency()))
                .map(this::mapToResponse)
                .toList();
    }

    public BigDecimal getBalance(Long accountId) {
        UUID uuid = UUID.randomUUID();
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountId)));
        return account.getBalance();
    }

    public void deactivateAccount(Long accountId) {
        UUID uuid = UUID.randomUUID();
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountId)));
        account.deactivate();
        accountRepository.save(account);
    }

    public void deactivateAccount(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId.toString()));
        account.deactivate();
        accountRepository.save(account);
    }

    public void updateBalance(Long accountId, BigDecimal newBalance) {
        UUID uuid = UUID.randomUUID();
        accountRepository.updateBalance(uuid, newBalance);
    }

    private String generateAccountNumber() {
        return String.valueOf(System.currentTimeMillis()).substring(1) + "0";
    }

    private AccountResponse mapToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus(),
                account.getOwnerDocument(),
                account.getOwnerName(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyRepository.java ===
package com.bank.accounts.infrastructure.idempotency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IdempotencyRepository {
    boolean existsByOperationKey(String operationKey);
    void save(IdempotencyRecord record);
    Optional<IdempotencyRecord> findByOperationKey(String operationKey);
    Optional<IdempotencyRecord> findByKey(String key);
    void deleteByOperationKey(String operationKey);
    void deleteExpiredRecords(LocalDateTime cutoffDate);
    long countByChannelAndCreatedAfter(String channel, LocalDateTime after);
    List<IdempotencyRecord> findByChannelAndCreatedBetween(String channel, LocalDateTime start, LocalDateTime end);

    record IdempotencyRecord(
            String operationKey,
            String channel,
            String responseBody,
            LocalDateTime createdAt,
            LocalDateTime expiresAt
    ) {}
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/model/Transfer.java ===
package com.bank.accounts.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {
    private UUID id;
    private UUID sourceAccountId;
    private UUID targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status;
    private String channel;
    private String idempotencyKey;
    private String operationNumber;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime failedAt;
    private String failureReason;
    private int retryCount;

    private static final int MAX_RETRY_COUNT = 3;

    public Transfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, String channel) {
        this.id = UUID.randomUUID();
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.fee = calculateFee(amount);
        this.status = "PENDING";
        this.channel = channel;
        this.operationNumber = generateOperationNumber();
        this.createdAt = LocalDateTime.now();
        this.retryCount = 0;
    }

    private BigDecimal calculateFee(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("1000")) > 0) {
            return amount.multiply(new BigDecimal("0.01"));
        }
        return BigDecimal.ZERO;
    }

    private String generateOperationNumber() {
        return "OP" + System.currentTimeMillis();
    }

    public static String generateIdempotencyKey(String channel, String operationNumber) {
        return channel + "-" + operationNumber + "-" + System.currentTimeMillis();
    }

    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }

    public boolean isProcessing() {
        return "PROCESSING".equalsIgnoreCase(status);
    }

    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isFailed() {
        return "FAILED".equalsIgnoreCase(status);
    }

    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(status);
    }

    public void markAsProcessing() {
        this.status = "PROCESSING";
    }

    public void markAsCompleted() {
        this.status = "COMPLETED";
        this.completedAt = LocalDateTime.now();
    }

    public void markAsFailed(String reason) {
        this.status = "FAILED";
        this.failedAt = LocalDateTime.now();
        this.failureReason = reason;
    }

    public void markAsRejected(String reason) {
        this.status = "REJECTED";
        this.failureReason = reason;
    }

    public boolean canRetry() {
        return retryCount < MAX_RETRY_COUNT;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public boolean isIdempotent(String newIdempotencyKey) {
        return this.idempotencyKey != null && this.idempotencyKey.equals(newIdempotencyKey);
    }

    public BigDecimal getTotalAmount() {
        return amount.add(fee != null ? fee : BigDecimal.ZERO);
    }

    public boolean isSameAccount() {
        return sourceAccountId.equals(targetAccountId);
    }

    public boolean isValidAmount() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isInterbank() {
        return false;
    }

    public boolean isHighValue() {
        return amount.compareTo(new BigDecimal("5000")) >= 0;
    }

    public long getProcessingTimeSeconds() {
        if (completedAt == null || createdAt == null) return 0;
        return java.time.Duration.between(createdAt, completedAt).getSeconds();
    }

    public String getStatusDescription() {
        return status != null ? status : "UNKNOWN";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(UUID sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public UUID getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(UUID targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public UUID getOriginAccountId() {
        return sourceAccountId;
    }

    public UUID getDestinationAccountId() {
        return targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(LocalDateTime failedAt) {
        this.failedAt = failedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/application/TransferService.java ===
package com.bank.accounts.application;

import com.bank.accounts.api.dto.TransferRequest;
import com.bank.accounts.api.dto.TransferResponse;
import com.bank.accounts.domain.exception.AccountNotFoundException;
import com.bank.accounts.domain.exception.InsufficientBalanceException;
import com.bank.accounts.domain.model.Account;
import com.bank.accounts.domain.model.Transfer;
import com.bank.accounts.domain.repository.AccountRepository;
import com.bank.accounts.infrastructure.audit.AuditEventPublisher;
import com.bank.accounts.infrastructure.idempotency.IdempotencyKeyGenerator;
import com.bank.accounts.infrastructure.idempotency.IdempotencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    private final AccountRepository accountRepository;
    private final IdempotencyRepository idempotencyRepository;
    private final IdempotencyKeyGenerator idempotencyKeyGenerator;
    private final AuditEventPublisher auditEventPublisher;
    private final AccountService accountService;

    public TransferService(
            AccountRepository accountRepository,
            IdempotencyRepository idempotencyRepository,
            IdempotencyKeyGenerator idempotencyKeyGenerator,
            AuditEventPublisher auditEventPublisher,
            AccountService accountService) {
        this.accountRepository = accountRepository;
        this.idempotencyRepository = idempotencyRepository;
        this.idempotencyKeyGenerator = idempotencyKeyGenerator;
        this.auditEventPublisher = auditEventPublisher;
        this.accountService = accountService;
    }

    public TransferResponse executeTransfer(TransferRequest request, String channel) {
        if (request.isSameAccount()) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (!request.isValidAmount()) {
            throw new IllegalArgumentException("Invalid transfer amount");
        }

        String idempotencyKey = request.hasExternalReference()
                ? request.generateOperationKey()
                : idempotencyKeyGenerator.generateKey(
                        String.valueOf(System.currentTimeMillis()), channel);

        var existingRecord = idempotencyRepository.findByOperationKey(idempotencyKey);
        if (existingRecord.isPresent()) {
            logger.info("Idempotent request detected: {}", idempotencyKey);
            return new TransferResponse(
                    UUID.randomUUID(),
                    idempotencyKey,
                    "COMPLETED",
                    request.amount(),
                    BigDecimal.ZERO,
                    LocalDateTime.now().minusHours(1),
                    LocalDateTime.now(),
                    null
            );
        }

        UUID sourceAccountId = UUID.randomUUID();
        UUID targetAccountId = UUID.randomUUID();

        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new AccountNotFoundException(sourceAccountId.toString()));

        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new AccountNotFoundException(targetAccountId.toString()));

        if (!sourceAccount.hasSufficientBalance(request.getAmountWithFee())) {
            throw new InsufficientBalanceException(
                    sourceAccount.getId().toString(),
                    sourceAccount.getAccountNumber(),
                    sourceAccount.getBalance(),
                    request.getAmountWithFee()
            );
        }

        sourceAccount.debit(request.getAmountWithFee());
        targetAccount.credit(request.amount());

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = new Transfer(sourceAccountId, targetAccountId, request.amount(), channel);
        transfer.setIdempotencyKey(idempotencyKey);
        transfer.markAsCompleted();

        idempotencyRepository.save(new IdempotencyRepository.IdempotencyRecord(
                idempotencyKey, channel, "COMPLETED", LocalDateTime.now(), LocalDateTime.now().plusDays(30)
        ));

        auditEventPublisher.publishTransferExecuted(transfer, sourceAccount, targetAccount);

        return mapToResponse(transfer);
    }

    public Transfer executeTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, Optional<String> idempotencyKey) {
        if (sourceAccountId.equals(targetAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        String key = idempotencyKey.orElseGet(() ->
                IdempotencyKeyGenerator.generateIdempotencyKey("DEFAULT", String.valueOf(System.currentTimeMillis())));

        var existingRecord = idempotencyRepository.findByKey(key);
        if (existingRecord.isPresent()) {
            logger.info("Idempotent request detected: {}", key);
            return existingRecord.get().responseBody() != null ?
                    new Transfer(sourceAccountId, targetAccountId, amount, "DEFAULT") : null;
        }

        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new AccountNotFoundException(sourceAccountId.toString()));

        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new AccountNotFoundException(targetAccountId.toString()));

        if (!sourceAccount.isActive()) {
            throw new IllegalStateException("Source account is not active");
        }

        if (!sourceAccount.hasSufficientBalance(amount)) {
            throw new InsufficientBalanceException(
                    sourceAccount.getId().toString(),
                    sourceAccount.getAccountNumber(),
                    sourceAccount.getBalance(),
                    amount
            );
        }

        sourceAccount.debit(amount);
        targetAccount.credit(amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = new Transfer(sourceAccountId, targetAccountId, amount, "DEFAULT");
        transfer.setIdempotencyKey(key);
        transfer.markAsCompleted();

        idempotencyRepository.save(new IdempotencyRepository.IdempotencyRecord(
                key, "DEFAULT", "COMPLETED", LocalDateTime.now(), LocalDateTime.now().plusDays(30)
        ));

        return transfer;
    }

    private TransferResponse mapToResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getIdempotencyKey(),
                transfer.getStatus(),
                transfer.getAmount(),
                transfer.getFee(),
                transfer.getCreatedAt(),
                transfer.getCompletedAt(),
                transfer.getFailureReason()
        );
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/api/dto/TransferResponse.java ===
package com.bank.accounts.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferResponse(
    UUID id,
    String operationKey,
    String status,
    BigDecimal amount,
    BigDecimal fee,
    LocalDateTime createdAt,
    LocalDateTime completedAt,
    String failureReason
) {
    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }

    public boolean isFailed() {
        return "FAILED".equalsIgnoreCase(status);
    }

    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(status);
    }

    public boolean hasFee() {
        return fee != null && fee.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getNetAmount() {
        return amount;
    }

    public String getFormattedAmount() {
        return String.format("%.2f", amount);
    }

    public String getFormattedFee() {
        return fee != null ? String.format("%.2f", fee) : "0.00";
    }

    public String getFormattedTotalAmount() {
        BigDecimal total = amount.add(fee != null ? fee : BigDecimal.ZERO);
        return String.format("%.2f", total);
    }

    public String getStatusDisplayName() {
        return status != null ? status : "UNKNOWN";
    }

    public long getProcessingTimeSeconds() {
        if (completedAt == null || createdAt == null) return 0;
        return java.time.Duration.between(createdAt, completedAt).getSeconds();
    }

    public boolean wasProcessedQuickly() {
        return getProcessingTimeSeconds() < 5;
    }
}

// === ARCHIVO: src/main/java/com/bank/accounts/domain/exception/InsufficientBalanceException.java ===
package com.bank.accounts.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException {
    private final UUID accountId;
    private final String accountNumber;
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final BigDecimal shortfall;

    public InsufficientBalanceException(UUID accountId, String accountNumber,
            BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(buildMessage(accountNumber, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.shortfall = requestedAmount.subtract(currentBalance);
    }

    public InsufficientBalanceException(String message) {
        super(message);
        this.accountId = null;
        this.accountNumber = null;
        this.currentBalance = null;
        this.requestedAmount = null;
        this.shortfall = null;
    }

    private static String buildMessage(String accountNumber, BigDecimal current, BigDecimal requested) {
        return String.format("Insufficient balance in account %s. Current: %s, Requested: %s",
                accountNumber, current, requested);
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public BigDecimal getShortfall() {
        return shortfall;
    }
}

```
