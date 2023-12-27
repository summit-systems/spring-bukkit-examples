plugins {
    id("spring-bukkit-examples.shared")
    id("spring-bukkit-examples.jpa")
    id("spring-bukkit-examples.test")

    id("net.minecrell.plugin-yml.bukkit")
}

bukkit {
    this.name = "Mute"
    this.version = "1.0.0"
    this.main = "kr.summitsystems.mute.MutePlugin"
    this.author = "vjh0107"
    this.apiVersion = "1.13"
    this.description = "Example plugin for SpringBukkit usage."
    this.commands {
        create("mute") {
            aliases = listOf("silent")
            permission = "mute.admin"
        }
    }
    this.libraries = listOf(
        libs.spring.bukkit.core,
        libs.spring.bukkit.jpa,
        libs.spring.bukkit.coroutines
    ).map { it.get().toString() }
}

dependencies {
    implementation(libs.spigot)
    implementation(libs.spring.bukkit.core)
    implementation(libs.spring.bukkit.jpa)
    implementation(libs.spring.bukkit.coroutines)
    testImplementation(libs.spigot)
}
