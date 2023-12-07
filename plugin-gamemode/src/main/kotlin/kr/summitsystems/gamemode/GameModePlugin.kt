package kr.summitsystems.gamemode

import kr.summitsystems.springbukkit.SpringBukkitPlugin

class GameModePlugin : SpringBukkitPlugin() {
    override fun getApplicationClass(): Class<*> {
        return GameModeApplication::class.java
    }
}