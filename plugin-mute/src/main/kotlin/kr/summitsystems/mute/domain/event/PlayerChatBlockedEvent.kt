package kr.summitsystems.mute.domain.event

import org.springframework.context.ApplicationEvent
import java.util.UUID

class PlayerChatBlockedEvent(
    source: Any,
    val playerId: UUID,
    val blockedMessage: String
) : ApplicationEvent(source)