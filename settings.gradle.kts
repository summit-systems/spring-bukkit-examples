rootProject.name = "spring-bukkit-examples"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"].toString()
        kotlin("jvm") version kotlinVersion apply false
        kotlin("plugin.spring") version kotlinVersion apply false
        kotlin("plugin.jpa") version kotlinVersion apply false

        val springBootVersion = extra["spring.boot.version"].toString()
        id("org.springframework.boot") version springBootVersion apply false

        id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("build-logic")
include("plugin-mute")
include("plugin-gamemode")
include("plugin-chatlogger")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    versionCatalogs {
        create("libs") {
            library("spigot", "org.spigotmc:spigot-api:${extra["spigot.version"]}")
            library("spring-bukkit-core", "kr.summitsystems:spring-bukkit-core:${extra["spring.bukkit.version"]}")
            library("spring-bukkit-coroutines", "kr.summitsystems:spring-bukkit-coroutines:${extra["spring.bukkit.version"]}")
            library("spring-bukkit-jpa", "kr.summitsystems:spring-bukkit-jpa:${extra["spring.bukkit.version"]}")
            library("spring-test", "org.springframework.boot:spring-boot-starter-test:${extra["spring.boot.version"]}")
        }
    }
}

