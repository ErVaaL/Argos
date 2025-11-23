plugins {
}

group = "com.erval.argos"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":application:argos-core"))

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:4.0.0")
}

tasks.test {
    useJUnitPlatform()
}
