package kr.summitsystems.chatlogger.infrastructure.persistence.repository

import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.summitsystems.chatlogger.domain.ChatLog
import kr.summitsystems.chatlogger.domain.ChatLogRepository
import org.springframework.stereotype.Repository

@Repository
class JpaDelegateChatLogRepository(private val jpaChatLogRepository: JpaChatLogRepository) : ChatLogRepository {
    @Transactional
    override suspend fun save(chatLog: ChatLog) {
        withContext(Dispatchers.IO) {
            jpaChatLogRepository.save(chatLog)
        }
    }
}