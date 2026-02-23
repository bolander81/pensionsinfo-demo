# Playwright Automation Framework

Dette projekt demonstrerer et skalerbart test-framework bygget i Java med Playwright. Projektet er struktureret til at håndtere tests på tværs af forskellige platforme og domæner.

---

## Test-scenarier i projektet

Frameworket indeholder i øjeblikket to forskellige flows:
1.  **Pensionsinfo:** End-to-end flow gennem demo-versionen af Pensionsinfo.dk for at verificere overblikssiden.
2.  **Login Flow:** Klassisk validering af login-funktionalitet (brugernavn, password og succes-beskeder) på Expand Testing.

---

## Struktur og Værktøjsvalg

Frameworket følger en lagdelt arkitektur for at sikre høj genanvendelighed og lav vedligeholdelse:

### 1. Arkitektur (Layered Approach)
* **TestContext:** Central styring af browserens livscyklus. Her konfigureres Tracing og Video-optagelse automatisk for hver testkørsel.
* **Page Object Model (Drivers):** Tekniske selektorer er isoleret i domæne-specifikke klasser:
    * `PensionsinfoPageDriver`: Håndterer elementer specifikt for Pensionsinfo.
    * `LoginPageDriver`: Håndterer login-formularer og feedback-beskeder.
* **DSL (Domain Specific Language):** Et abstraktionslag (`PensionsinfoDsl` og `LoginDsl`), der gør det muligt at læse selve testen som forretningslogik fremfor teknisk kode.

### 2. Valg af værktøjer
* **Playwright (Java):** Valgt pga. sin hastighed og overlegne debugging-værktøjer som **Tracing**, der muliggør detaljeret fejlfinding via snapshots af DOM'en.
* **JUnit 5:** Anvendes som test-runner for nem integration med IDE og CI/CD.

### 3. Eksekvering og Rapportering
Tests køres via Maven:
```bash
mvn test