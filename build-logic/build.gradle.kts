import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    `kotlin-dsl`
}

val properties = loadProperties(rootProject.gradle.parent!!.rootProject.projectDir.path + "/gradle.properties")

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${properties["kotlin.version"]}")
    implementation("org.jetbrains.kotlin.plugin.spring:org.jetbrains.kotlin.plugin.spring.gradle.plugin:${properties["kotlin.version"]}")
    implementation("org.jetbrains.kotlin.plugin.jpa:org.jetbrains.kotlin.plugin.jpa.gradle.plugin:${properties["kotlin.version"]}")

    implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:${properties["spring.boot.version"]}")
}