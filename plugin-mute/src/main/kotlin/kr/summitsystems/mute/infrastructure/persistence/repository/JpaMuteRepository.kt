package kr.summitsystems.mute.infrastructure.persistence.repository

import kr.summitsystems.mute.domain.Mute
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.*

interface JpaMuteRepository : JpaRepository<Mute, Long> {
    fun findMuteEntitiesByPlayerId(playerId: UUID): List<Mute>

    fun findMuteEntitiesByPlayerIdAndExpiresAtAfter(playerId: UUID, time: LocalDateTime): List<Mute>

    fun findFirstByPlayerIdAndExpiresAtAfterOrderByExpiresAtDesc(playerId: UUID, time: LocalDateTime): Mute?
}