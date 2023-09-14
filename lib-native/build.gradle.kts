import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlin_version: String by project

plugins {
    kotlin("jvm")
}

group = "com.isyscore.wh.native"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-scripting-jsr223:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlin_version")

    testImplementation("org.jetbrains.kotlin:kotlin-scripting-jsr223:$kotlin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:$kotlin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlin_version")

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