import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlin_version: String by project

plugins {
    kotlin("jvm")
}

group = "com.isyscore.wh.hour"

dependencies {
    implementation(project(":lib-common"))
    implementation(project(":lib-database"))
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