# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de un microservicio de gestión de cuentas bancarias**.

| | |
|---|---|
| Tema | Microservicio REST en entorno de banca |
| Nivel | junior-l1 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java / Spring Boot 3.3.4 |
| Patron arquitectonico | hexagonal/clean con capas estándar (api, application, domain, infrastructure) |
| Tiempo estimado | 8 horas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Crear y consultar cuentas**: Microservicio REST operativo que permite crear y consultar cuentas bancarias.
- **Fase 2 — Realizar transferencias entre cuentas**: Microservicio REST extendido que permite realizar transferencias idempotentes entre cuentas bancarias.
- **Fase 3 — Optimización y refactorización**: Microservicio REST optimizado y refactorizado, con documentación de las mejoras realizadas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### 2. Referencias colgando (33)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/accounts/application/TransferService.java` — `IdempotencyRecord`
      IdempotencyRecord se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.bank.accounts.infrastructure.idempotency.IdempotencyRecord.
- [ ] `src/main/java/com/bank/accounts/api/AccountController.java` — `AuditEventPublisher.publishAccountCreated`
      Se invoca `publishAccountCreated` sobre `AuditEventPublisher`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/AccountController.java` — `AccountResponse.accountId`
      Se invoca `accountId` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/AccountController.java` — `AccountResponse.accountNumber`
      Se invoca `accountNumber` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/AccountController.java` — `AccountNotFoundException.getMessage`
      Se invoca `getMessage` sobre `AccountNotFoundException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/AccountController.java` — `AuditEventPublisher.publishAccountAccessed`
      Se invoca `publishAccountAccessed` sobre `AuditEventPublisher`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/AccountController.java` — `AuditEventPublisher.publishAccountDeactivated`
      Se invoca `publishAccountDeactivated` sobre `AuditEventPublisher`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferRequest.originAccountId`
      Se invoca `originAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferRequest.destinationAccountId`
      Se invoca `destinationAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.originAccountId`
      Se invoca `originAccountId` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.destinationAccountId`
      Se invoca `destinationAccountId` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.amount`
      Se invoca `amount` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferResponse.transferId`
      Se invoca `transferId` sobre `TransferResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferService.getTransfer`
      Se invoca `getTransfer` sobre `TransferService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferService.getTransfersByAccount`
      Se invoca `getTransfersByAccount` sobre `TransferService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/api/TransferController.java` — `TransferService.getAllTransfers`
      Se invoca `getAllTransfers` sobre `TransferService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.accountNumber`
      Se invoca `accountNumber` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.accountType`
      Se invoca `accountType` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.currency`
      Se invoca `currency` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.ownerDocument`
      Se invoca `ownerDocument` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/application/AccountService.java` — `AccountRequest.ownerName`
      Se invoca `ownerName` sobre `AccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/application/TransferService.java` — `TransferRequest.amount`
      Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `Transfer.getSourceAccountNumber`
      Se invoca `getSourceAccountNumber` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `Transfer.getTargetAccountNumber`
      Se invoca `getTargetAccountNumber` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `Transfer.getExternalReference`
      Se invoca `getExternalReference` sobre `Transfer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `AuditEvent.getEventType`
      Se invoca `getEventType` sobre `AuditEvent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java` — `AuditEvent.getEventId`
      Se invoca `getEventId` sobre `AuditEvent`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.id`
      Se invoca `id` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.accountNumber`
      Se invoca `accountNumber` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.balance`
      Se invoca `balance` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.currency`
      Se invoca `currency` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.size`
      Se invoca `size` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/accounts/application/AccountServiceTest.java` — `AccountResponse.get`
      Se invoca `get` sobre `AccountResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (26)

- `pom.xml`
- `src/main/java/com/bank/accounts/AccountsApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/accounts/api/dto/AccountRequest.java`
- `src/main/java/com/bank/accounts/api/dto/AccountResponse.java`
- `src/main/java/com/bank/accounts/api/dto/TransferRequest.java`
- `src/main/java/com/bank/accounts/api/dto/TransferResponse.java`
- `src/main/java/com/bank/accounts/domain/model/Account.java`
- `src/main/java/com/bank/accounts/domain/model/Transfer.java`
- `src/main/java/com/bank/accounts/domain/repository/AccountRepository.java`
- `src/main/java/com/bank/accounts/infrastructure/persistence/AccountJpaRepository.java`
- `src/main/java/com/bank/accounts/domain/exception/InsufficientBalanceException.java`
- `src/main/java/com/bank/accounts/domain/exception/AccountNotFoundException.java`
- `src/main/java/com/bank/accounts/domain/exception/DuplicateAccountException.java`
- `src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyRepository.java`
- `src/main/java/com/bank/accounts/api/AccountController.java`
- `src/main/java/com/bank/accounts/api/TransferController.java`
- `src/main/java/com/bank/accounts/application/AccountService.java`
- `src/main/java/com/bank/accounts/application/TransferService.java`
- `src/main/java/com/bank/accounts/infrastructure/audit/AuditEventPublisher.java`
- `src/main/java/com/bank/accounts/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/java/com/bank/accounts/infrastructure/idempotency/IdempotencyKeyGenerator.java`
- `src/test/java/com/bank/accounts/api/AccountControllerTest.java`
- `src/test/java/com/bank/accounts/api/TransferControllerTest.java`
- `src/test/java/com/bank/accounts/application/AccountServiceTest.java`
- `src/test/java/com/bank/accounts/application/TransferServiceTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/accounts`
- `src/main/java/com/bank/accounts/api`
- `src/main/java/com/bank/accounts/application`
- `src/main/java/com/bank/accounts/domain`
- `src/main/java/com/bank/accounts/infrastructure`
- `src/main/resources`
- `src/test/java/com/bank/accounts`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas estándar (api, application, domain, infrastructure)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Crear un microservicio REST con Spring Boot, JPA y H2

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
