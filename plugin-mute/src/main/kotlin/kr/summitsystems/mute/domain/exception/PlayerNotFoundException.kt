package kr.summitsystems.mute.domain.exception

import java.util.UUID

class PlayerNotFoundException(val playerId: UUID) : MuteException("Player not found with id: $playerId")