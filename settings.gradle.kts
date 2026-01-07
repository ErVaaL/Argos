rootProject.name = "argos-v2"

include(
    ":contracts",
    ":services:resource-service:resource-core",
    ":services:resource-service:resource-application",
    ":services:resource-service:resource-adapters:grpc",
    ":services:resource-service:resource-adapters:mongo",
    ":services:resource-service:resource-bootstrap",

    ":services:process-service:process-core",
    ":services:process-service:process-application",
    ":services:process-service:process-adapters:grpc",
    ":services:process-service:process-adapters:mongo",
    ":services:process-service:process-adapters:rabbitmq",
    ":services:process-service:process-adapters:excel",
    ":services:process-service:process-bootstrap",

    ":services:report-service:report-core",
    ":services:report-service:report-application",
    ":services:report-service:report-adapters:grpc",
    ":services:report-service:report-adapters:mongo",
    ":services:report-service:report-adapters:rabbitmq",
    ":services:report-service:report-adapters:pdf",
    ":services:report-service:report-adapters:storage",
    ":services:report-service:report-bootstrap",
)
