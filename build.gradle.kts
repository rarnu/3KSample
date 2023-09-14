group = "com.isyscore.wh"
version = "1.0.0"

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.3"
    kotlin("plugin.serialization") version "1.9.0"
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}
