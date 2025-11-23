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
    implementation(project(":application:adapters:argos-mongo-adapter"))
    implementation(project(":application:adapters:argos-m2m-security"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
