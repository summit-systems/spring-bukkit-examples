plugins {
    id("spring-bukkit-examples.shared")
    id("spring-bukkit-examples.jpa")
    id("spring-bukkit-examples.test")

    id("net.minecrell.plugin-yml.bukkit")
}

bukkit {
    this.name = "ChatLogger"
    this.version = "1.0.0"
    this.main = "kr.summitsystems.chatlogger.ChatLoggerPlugin"
    this.author = "vjh0107"
    this.apiVersion = "1.13"
    this.description = "Example plugin for SpringBukkit usage."
    this.libraries = listOf(
        libs.spring.bukkit.core,
        libs.spring.bukkit.jpa
    ).map { it.get().toString() }
}

dependencies {
    implementation(libs.spigot)
    implementation(libs.spring.bukkit.core)
    implementation(libs.spring.bukkit.jpa)
    testImplementation(libs.spigot)
}
