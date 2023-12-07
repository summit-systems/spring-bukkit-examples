package kr.summitsystems.mute.domain.service

import kr.summitsystems.mute.domain.Mute
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

interface MuteService {
    suspend fun applyMute(playerId: UUID, time: Duration)

    suspend fun isOnMute(playerId: UUID): Boolean

    suspend fun getMuteExpiresWhen(playerId: UUID): LocalDateTime

    suspend fun getUnexpiredMutes(playerId: UUID): List<Mute>
}