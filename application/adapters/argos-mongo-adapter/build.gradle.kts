plugins {
}

group = "com.erval.argos"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":application:argos-core"))
    implementation(project(":application:argos-application"))

    implementation("org.springframework.boot:spring-boot-startet-data-mongodb")
}

tasks.test {
    useJUnitPlatform()
}
