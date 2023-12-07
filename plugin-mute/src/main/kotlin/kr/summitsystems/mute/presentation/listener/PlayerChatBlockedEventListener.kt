package kr.summitsystems.mute.presentation.listener

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.summitsystems.mute.domain.event.PlayerChatBlockedEvent
import kr.summitsystems.mute.domain.service.MuteService
import org.bukkit.Server
import org.bukkit.entity.Player
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class PlayerChatBlockedEventListener(
    private val muteService: MuteService,
    private val coroutineScope: CoroutineScope,
    private val server: Server,
    private val messageSource: MessageSource
) {
    private companion object {
        const val MESSAGE_DATETIME_FORMAT_KEY = "message.datetime.format"
        const val MESSAGE_CHAT_BLOCKED_KEY = "message.chat.blocked"
    }

    private val logger: Logger = LoggerFactory.getLogger(PlayerChatBlockedEventListener::class.java)

    @EventListener
    fun onChatBlocked(event: PlayerChatBlockedEvent) {
        val player = server.getPlayer(event.playerId)
        if (player == null) {
            logger.warn("Player with id {} is not on the server.", event.playerId)
            return
        }
        val playerLocale = getPlayerLocale(player)
        coroutineScope.launch {
            val dateTimeFormatter = messageSource.getMessage(MESSAGE_DATETIME_FORMAT_KEY, arrayOf(), playerLocale)
                .let { DateTimeFormatter.ofPattern(it) }
            val expiresWhen = muteService.getMuteExpiresWhen(player.uniqueId).format(dateTimeFormatter)
            val message = messageSource.getMessage(MESSAGE_CHAT_BLOCKED_KEY, arrayOf(expiresWhen), playerLocale)
            player.sendMessage(message)
        }
    }

    private fun getPlayerLocale(player: Player): Locale {
        return player.locale.split("_").let {
            Locale(it.component1(), it.component2())
        }
    }
}