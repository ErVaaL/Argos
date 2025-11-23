plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot") version "3.5.5" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "com.erval.argos"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.42")
        annotationProcessor("org.projectlombok:lombok:1.18.42")

        testCompileOnly("org.projectlombok:lombok:1.18.42")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.42")

        testImplementation("org.junit.jupiter:junit-jupiter:5.14.1")
    }

    tasks.test {
        useJUnitPlatform()
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}

tasks.register<JacocoReport>("jacocoRootReport") {
    dependsOn(subprojects.map { it.tasks.named("test") })

    executionData.from(subprojects.map { it.layout.buildDirectory.file("jacoco/test.exec") })

    subprojects.forEach { sub ->
        sourceSets(sub.extensions.getByType<SourceSetContainer>()["main"])

        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}
