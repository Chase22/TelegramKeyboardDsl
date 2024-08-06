plugins {
    kotlin("jvm") version "1.9.23"
}

group = "de.chasenet"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.telegram:telegrambots-meta:7.4.0")
    implementation("org.telegram:telegrambots-client:7.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}