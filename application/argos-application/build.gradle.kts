plugins {
}

group = "com.erval.argos"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":application:argos-core"))
}

tasks.test {
    useJUnitPlatform()
}
