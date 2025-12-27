plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.erval.argos"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":application:argos-core"))
    implementation(project(":application:argos-application"))
    implementation(project(":application:adapters:argos-mongo-adapter"))
    implementation(project(":application:adapters:argos-m2m-security"))

    implementation("org.springframework.boot:spring-boot-starter-web:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-graphql:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-security:4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:4.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.0")
}

tasks.test {
    useJUnitPlatform()
}
