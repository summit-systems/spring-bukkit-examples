package kr.summitsystems.chatlogger

import kr.summitsystems.springbukkit.SpringBukkitPlugin

class ChatLoggerPlugin : SpringBukkitPlugin() {
    override fun getApplicationClass(): Class<*> {
        return ChatLoggerApplication::class.java
    }
}