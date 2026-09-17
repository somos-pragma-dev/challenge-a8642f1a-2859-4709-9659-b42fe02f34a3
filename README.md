# Desarrollo de un microservicio de gestión de cuentas bancarias

En el contexto de una institución financiera, necesitas desarrollar un microservicio REST que gestione operaciones básicas de cuentas bancarias. El microservicio deberá permitir crear cuentas, consultar saldos y realizar transferencias entre cuentas. El sistema debe manejar correctamente las validaciones de negocio, como saldos insuficientes y cuentas inexistentes, y garantizar la idempotencia en las operaciones de transferencia. El dominio incluye los siguientes actores: 'originador de créditos','motor antifraude', 'buró de riesgos', 'core bancario', 'gateway de pagos','sistema de liquidación', 'agente de retención', 'consolidador contable'. Las operaciones deben ser idempotentes con una clave basada en el número de operación y el canal de solicitud. El sistema debe soportar un throughput de 1 500 solicitudes por segundo en hora pico.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Microservicio REST en entorno de banca |
| **Nivel** | junior-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Crear y consultar cuentas

**Objetivo:** Implementar la funcionalidad para crear y consultar cuentas bancarias.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- El microservicio debe permitir la creación de nuevas cuentas con número de cuenta, titular y saldo inicial.
- Debe ser posible consultar el saldo de una cuenta existente.
- Las cuentas creadas deben persistir en una base de datos en memoria.
- Validar que el número de cuenta sea único y que el saldo inicial sea positivo.

**Entregable:** Microservicio REST operativo que permite crear y consultar cuentas bancarias.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo estructurar la información de la cuenta para facilitar futuras operaciones.
- Piensa en cómo manejarías la persistencia de datos en un entorno real.

</details>

### Fase 2: Realizar transferencias entre cuentas

**Objetivo:** Implementar la funcionalidad para realizar transferencias entre cuentas bancarias.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- El microservicio debe permitir realizar transferencias entre cuentas existentes.
- Validar que las cuentas origen y destino existan y que el saldo de la cuenta origen sea suficiente para la transferencia.
- Garantizar la idempotencia de las transferencias utilizando una clave basada en el número de operación y el canal de solicitud.
- Emitir un evento al sistema de auditoría por cada transferencia realizada.

**Entregable:** Microservicio REST extendido que permite realizar transferencias idempotentes entre cuentas bancarias.

<details>
<summary>Pistas de conocimiento</summary>

- Reflexiona sobre cómo manejarías los edge cases, como transferencias con saldo insuficiente.
- Considera cómo implementarías la idempotencia en las transferencias.

</details>

### Fase 3: Optimización y refactorización

**Objetivo:** Optimizar y refactorizar el microservicio para mejorar su rendimiento y mantenibilidad.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza el código existente y propone mejoras para optimizar el rendimiento y la mantenibilidad del microservicio.
- Implementa las mejoras propuestas y verifica que el microservicio continúe funcionando correctamente.
- Documenta las mejoras realizadas y justifica tus decisiones.

**Entregable:** Microservicio REST optimizado y refactorizado, con documentación de las mejoras realizadas.

<details>
<summary>Pistas de conocimiento</summary>

- Reflexiona sobre cómo podrías mejorar la estructura del código para facilitar futuras extensiones.
- Considera cómo podrías optimizar el rendimiento del microservicio sin comprometer su funcionalidad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una cuenta bancaria en el contexto de este microservicio?
- **paraQueSirve**: ¿Para qué sirve la funcionalidad de realizar transferencias entre cuentas?
- **comoSeUsa**: ¿Cómo se usa la idempotencia en las transferencias?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir al realizar transferencias?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización y refactorización del microservicio?

## Criterios de Evaluacion

- Implementación correcta de la funcionalidad para crear y consultar cuentas.
- Implementación correcta de la funcionalidad para realizar transferencias idempotentes entre cuentas.
- Optimización y refactorización del microservicio con documentación clara y justificada.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
