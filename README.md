# Argos

Microservice + microfrontend stack for Argos, orchestrated with Docker Compose.

## System requirements
- Docker (with Compose plugin)

## Quick start (full stack)
```bash
docker compose up -d
```

## OR for first run:
```bash
docker compose up -d --pull always
```

Traefik routes everything under `*.localhost` (RFC 6761). If your OS does not resolve these automatically, add the hosts to your local DNS or `/etc/hosts`.

## Services (compose.yaml)
- Traefik: `http://localhost` (edge) and dashboard at `http://localhost:8088`
- Keycloak: `http://auth.localhost` (admin/admin)
- MongoDB: internal only, volume `mongo_data`
- RabbitMQ: `http://localhost:15672` (guest/guest)
- Resource service: `http://localhost/api/v1/resource/*`
- Process service: `http://localhost/api/v1/process/*`
- Report service: `http://localhost/api/v1/report/*`
- Host (shell): `http://argos.localhost`
- Remote Query MFE: `http://query.argos.localhost`
- Remote Report MFE: `http://report.argos.localhost`

## Frontend-only compose (frontend/compose.frontend.yaml)
Use this if you are iterating on the MFEs without the full backend stack:
```bash
cd frontend
docker compose -f compose.frontend.yaml up -d
```

Frontend URLs:
- Host: `http://localhost:8080`
- Remote Query: `http://localhost:8081`
- Remote Report: `http://localhost:8082`

Configuration for the host shell lives at `frontend/config/host.config.json`.

## Keycloak realm import
The realm import used in compose is `keycloak/import/argos-realm.json`. It configures:
- Realm: `argos`
- Frontend client: `argos-frontend` (public client, PKCE)
- Admin user: `admin` / `admin`

## Data volumes
- `mongo_data` for MongoDB
- `report_data` for generated reports
- `keycloak_data` for Keycloak
