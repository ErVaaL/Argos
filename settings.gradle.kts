rootProject.name = "argos-v2"

include(
    ":contracts",
    "services:resource-service:resource-core",
    "services:resource-service:resource-application",
    "services:resource-service:resource-adapters:mongo",
    "services:resource-service:resource-bootstrap",
    "services:process-service:process-core",
    "services:process-service:process-application",
    "services:process-service:process-adapters:grpc",
    "services:process-service:process-adapters:mongo",
    "services:process-service:process-bootstrap",
)
