package kr.summitsystems.mute.domain.repository

import kr.summitsystems.mute.domain.Mute
import java.util.UUID

interface MuteRepository {
    suspend fun findByPlayerId(playerId: UUID): List<Mute>

    suspend fun findUnexpiredMutesByPlayerId(playerId: UUID): List<Mute>

    suspend fun findLongestMute(playerId: UUID): Mute?

    suspend fun findAll(): List<Mute>

    suspend fun save(mute: Mute): Mute
}