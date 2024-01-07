package kr.summitsystems.mute.domain.service

import com.google.common.base.Preconditions
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.summitsystems.mute.domain.Mute
import kr.summitsystems.mute.domain.repository.MuteRepository
import kr.summitsystems.springbukkit.core.checker.annotation.EnsuresAsyncThread
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Service
class TxMuteService(
    private val repository: MuteRepository
) : MuteService {

    @Transactional
    @EnsuresAsyncThread
    override suspend fun applyMute(playerId: UUID, time: Duration) {
        Preconditions.checkArgument(!time.isNegative && !time.isZero)

        val expiresAt = LocalDateTime.now().plus(time)
        val mute = Mute(null, playerId, expiresAt)
        withContext(Dispatchers.IO) {
            repository.save(mute)
        }
    }

    @Transactional
    @EnsuresAsyncThread
    override suspend fun getMuteExpiresWhen(playerId: UUID): LocalDateTime {
        val longestMute = withContext(Dispatchers.IO) {
            repository.findLongestMute(playerId)
        } ?: throw IllegalArgumentException("Please check if the player is muted before.")
        return longestMute.expiresAt
    }

    @Transactional
    @EnsuresAsyncThread
    override suspend fun isOnMute(playerId: UUID): Boolean {
        val unexpiredMutes = withContext(Dispatchers.IO) {
            repository.findUnexpiredMutesByPlayerId(playerId)
        }
        return unexpiredMutes.isNotEmpty()
    }

    @Transactional
    @EnsuresAsyncThread
    override suspend fun getUnexpiredMutes(playerId: UUID): List<Mute> {
        return withContext(Dispatchers.IO) {
            repository.findUnexpiredMutesByPlayerId(playerId)
        }
    }
}