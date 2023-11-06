val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val postgresql_driver_version: String by project
val flyway_version: String by project

plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.5"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("org.flywaydb.flyway") version "9.22.1"
}

group = "com.github.chMatvey"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-request-validation")
    implementation("io.ktor:ktor-server-status-pages")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    runtimeOnly("org.postgresql:postgresql:${postgresql_driver_version}")
    implementation("org.flywaydb:flyway-core:${flyway_version}")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
