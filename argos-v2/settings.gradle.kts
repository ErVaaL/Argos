rootProject.name = "argos-v2"

include(
    "services:resource-service:resource-core",
    "services:resource-service:resource-application",
    "services:resource-service:resource-adapters:mongo",
    "services:resource-service:resource-bootstrap"
)
