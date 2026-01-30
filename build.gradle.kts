import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    `java-library`
    kotlin("jvm") version "2.2.0"
}

group = "org.densy.polyglot.extension"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.densy.org/snapshots")
}

dependencies {
    api("org.densy.polyglot:api:1.1.1-SNAPSHOT")
    api("org.densy.polyglot:core:1.1.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "extension-kotlin"
            version = project.version.toString()
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "densy"
            url = uri("https://repo.densy.org/snapshots")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    freeCompilerArgs.set(listOf("-Xcontext-parameters"))
}