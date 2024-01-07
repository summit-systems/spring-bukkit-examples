package kr.summitsystems.mute

import kr.summitsystems.springbukkit.core.SpringBukkitPlugin

class MutePlugin : SpringBukkitPlugin() {
    override fun getApplicationClass(): Class<*> {
        return MuteApplication::class.java
    }
}