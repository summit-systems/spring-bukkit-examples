package kr.summitsystems.mute.presentation.listener

import kr.summitsystems.mute.domain.event.PlayerChatBlockedEvent
import kr.summitsystems.mute.domain.service.MuteService
import kr.summitsystems.springbukkit.core.checker.annotation.EnsuresAsyncThread
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class PlayerChatListener(
    private val muteService: MuteService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @EnsuresAsyncThread
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    suspend fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        if (muteService.isOnMute(player.uniqueId)) {
            event.isCancelled = true
            applicationEventPublisher.publishEvent(PlayerChatBlockedEvent(this, player.uniqueId, event.message))
        }
    }
}