package kr.summitsystems.chatlogger.application.listener

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.summitsystems.chatlogger.domain.ChatLog
import kr.summitsystems.chatlogger.domain.ChatLogRepository
import kr.summitsystems.springbukkit.checker.annotation.EnsuresAsyncThread
import kr.summitsystems.springbukkit.listener.annotation.BukkitListener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.springframework.stereotype.Component

@Component
class PlayerChatListener(
    private val repository: ChatLogRepository,
    private val coroutineScope: CoroutineScope
) {
    @EnsuresAsyncThread
    @BukkitListener
    suspend fun onChat(event: AsyncPlayerChatEvent) {
        coroutineScope.launch(Dispatchers.IO) {
            val chatLog = ChatLog(null, event.player.uniqueId, event.message)
            repository.save(chatLog)
        }
    }
}