package kr.summitsystems.gamemode

import kr.summitsystems.springbukkit.core.SpringBukkitPlugin

class GameModePlugin : SpringBukkitPlugin() {
    override fun getApplicationClass(): Class<*> {
        return GameModeApplication::class.java
    }
}