plugins {
    id("spring-bukkit-examples.shared")
    id("spring-bukkit-examples.test")

    id("net.minecrell.plugin-yml.bukkit")
}

bukkit {
    this.name = "GameMode"
    this.version = "1.0.0"
    this.main = "kr.summitsystems.gamemode.GameModePlugin"
    this.author = "vjh0107"
    this.apiVersion = "1.13"
    this.description = "Example plugin for SpringBukkit usage."
    this.commands {
        create("gamemode") {
            aliases = listOf("gm")
            permission = "gamemode.gamemode"
        }
    }
    this.libraries = listOf(
        libs.spring.bukkit.starter
    ).map { it.get().toString() }
}

dependencies {
    implementation(libs.spigot)
    implementation(libs.spring.bukkit.starter)
    testImplementation(libs.spigot)
}
