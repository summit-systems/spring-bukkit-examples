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
        libs.spring.data.jpa,
        libs.hibernate,
        libs.hikaricp,
        libs.spring.bukkit.core
    ).map { it.get().toString() }
}

dependencies {
    implementation(libs.spigot)
    implementation(libs.spring.bukkit.core)
    implementation(libs.spring.data.jpa)
    implementation(libs.hibernate)
    testImplementation(libs.spigot)
}
