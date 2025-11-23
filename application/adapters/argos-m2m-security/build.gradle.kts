plugins {
    `java-library`
}

group = "com.erval.argos"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:4.0.0")
    api("com.m2m:internal-auth-server:1.0-SNAPSHOT")
    implementation("com.m2m:m2m-internal-auth-lib:1.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
