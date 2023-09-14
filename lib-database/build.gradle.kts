import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlin_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm")
}

group = "com.isyscore.wh.database"

dependencies {
    api("org.ktorm:ktorm-core:3.6.0")
    implementation("org.ktorm:ktorm-jackson:3.6.0")
    implementation("com.alibaba:druid:1.2.18")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.ktorm:ktorm-support-mysql:3.6.0")

    val dialects = arrayOf("mysql", "postgresql", "sqlite", "oracle", "sqlserver")
    dialects.forEach { dialect ->
        implementation("org.ktorm:ktorm-support-${dialect}:3.6.0")
    }

    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation(project(":lib-common"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}