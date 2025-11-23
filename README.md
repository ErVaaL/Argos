# Argos

Lightweight IoT telemetry stack: domain layer, application services, MongoDB adapters, and a GraphQL API (with a small internal REST token endpoint) for managing devices and measurements.

## System requirements
- Java 21 (Java 17+ works; project toolchain targets 21)
- Gradle (wrapper provided: `./gradlew`)
- MongoDB 8 (see `run-mongo.sh` for a quick local instance)

## Setup and installation
```bash
git clone <repo-url>
cd Argos

# Optional: start Mongo locally
./run-mongo.sh

# Or:
docker run -d -p 27017:27017 --name argos-mongo -e MONGO_INITDB_DATABASE=argos mongo:8.0

# Build and run tests
./gradlew test
```

## Configuration
- Primary config file: `public-services/argos-api/src/main/resources/application.yaml`.
- Example properties (if you prefer `.properties`):
  ```
  spring.data.mongodb.uri=mongodb://root:rootpass@localhost:27017/argos?authSource=admin
  internal.oauth.server.issuer=argos
  internal.oauth.clients.mockservice1.secret=somesecret
  internal.oauth.clients.mockservice1.scopes=SCOPE_SEND_INFORMATION
  ```
- Internal OAuth keys are loaded from classpath PEMs (`application/adapters/argos-m2m-security/src/main/resources/keys/internal-oauth-*.pem`); ensure they exist or point to your own.

## Running the application
- With Gradle: `./gradlew :public-services:argos-api:bootRun` (or run `ArgosApiApplication` from your IDE).
- With built JAR: `./gradlew :public-services:argos-api:bootJar` and then `java -jar public-services/argos-api/build/libs/argos-api-0.0.1-SNAPSHOT.jar` (ensure dependencies are available if not building a fat jar).

## API endpoints
- GraphQL: `POST /graphql`
  - Queries: `devices(filter, page)`, `measurements(filter, page)`
  - Mutations: `createDevice`, `updateDevice`, `deleteDevice`, `createMeasurement`, `deleteMeasurement`
  - Schema: `public-services/argos-api/src/main/resources/graphql/schema.graphqls`
- REST (internal token): `POST /internal/oauth/token`
  - Form data: `grant_type=client_credentials`, optional `scope=...`
  - Auth: `Authorization: Basic <base64(clientId:secret)>`

## Tests and coverage
- Run tests: `./gradlew test`
- Generate JaCoCo reports: `./gradlew test jacocoTestReport`
- HTML reports:
  - `application/argos-core/build/reports/jacoco/test/html/index.html`
  - `application/argos-application/build/reports/jacoco/test/html/index.html`
  - `application/adapters/argos-mongo-adapter/build/reports/jacoco/test/html/index.html`

## MongoDB helper
- Local Mongo: Run script `./run-mongo.sh` to start a Mongo container or just copy the docker command inside to start it manually.
- Default URI base used in config examples: `mongodb://localhost:27017/argos`
