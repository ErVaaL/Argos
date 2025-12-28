# Argos RabbitMQ Contracts

These JSON files describe message payloads exchanged between
Argos microservices via RabbitMQ.

## Events

### report.requested.v1
Emitted by: process-service
Consumed by: reports-service

### report.generated.v1
Emitted by: reports-service
Consumed by: process-service

### report.failed.v1
Emitted by: reports-service
Consumed by: process-service

