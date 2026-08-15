# sharazan-db

**Sharazan** — модульный бэкенд-фреймворк на Kotlin, вдохновлённый архитектурой Ktor (declare-then-start композиция через Koin, без Spring-магии).

**db** — доступ к Postgres через Exposed + HikariCP: `ExposedDatabase` (управление пулом соединений и жизненным циклом) и `query { }` — единая точка входа для выполнения запросов вне `Dispatchers.Default`.

## Стек

- Exposed (core + jdbc)
- HikariCP
- PostgreSQL JDBC
- core, logging (sharazan)

## Maven-координаты

```kotlin
implementation("com.github.37hulk37:sharazan-db:1.0.0")
```
