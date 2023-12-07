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
            library("spring-bukkit-core", "kr.summitsystems:spring-bukkit-core:${extra["spring.bukkit.version"]}")

            library("spigot", "org.spigotmc:spigot-api:${extra["spigot.version"]}")
            library("kotlin-stdlib", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${extra["kotlin.version"]}")
            library("kotlinx-coroutines-core", "org.jetbrains.kotlinx:kotlinx-coroutines-core:${extra["kotlinx.coroutines.version"]}")

            library("spring-data-jpa", "org.springframework.data:spring-data-jpa:${extra["spring.data.version"]}")
            library("hibernate", "org.hibernate.orm:hibernate-core:${extra["hibernate.version"]}")
            library("hikaricp", "com.zaxxer:HikariCP:${extra["hikaricp.version"]}")
            library("spring-test", "org.springframework.boot:spring-boot-starter-test:${extra["spring.boot.version"]}")
        }

    }
}

