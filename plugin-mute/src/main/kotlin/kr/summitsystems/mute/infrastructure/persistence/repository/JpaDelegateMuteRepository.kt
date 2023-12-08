package kr.summitsystems.mute.infrastructure.persistence.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.summitsystems.mute.domain.Mute
import kr.summitsystems.mute.domain.repository.MuteRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class JpaDelegateMuteRepository(private val repository: JpaMuteRepository) : MuteRepository {
    override suspend fun findByPlayerId(playerId: UUID): List<Mute> {
        return withContext(Dispatchers.IO) {
            repository.findMuteEntitiesByPlayerId(playerId)
        }
    }

    override suspend fun findUnexpiredMutesByPlayerId(playerId: UUID): List<Mute> {
        return withContext(Dispatchers.IO) {
            repository.findMuteEntitiesByPlayerIdAndExpiresAtAfter(playerId, LocalDateTime.now())
        }
    }

    override suspend fun findLongestMute(playerId: UUID): Mute? {
        return withContext(Dispatchers.IO) {
            repository.findFirstByPlayerIdAndExpiresAtAfterOrderByExpiresAtDesc(playerId, LocalDateTime.now())
        }
    }

    override suspend fun save(mute: Mute): Mute {
        return withContext(Dispatchers.IO) {
            repository.save(mute)
        }
    }

    override suspend fun findAll(): List<Mute> {
        return withContext(Dispatchers.IO) {
            repository.findAll()
        }
    }
}