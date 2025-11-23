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

    testImplementation("org.springframework.boot:spring-boot-starter-test:4.0.0")

    testImplementation("org.testcontainers:mongodb:1.21.3")
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
