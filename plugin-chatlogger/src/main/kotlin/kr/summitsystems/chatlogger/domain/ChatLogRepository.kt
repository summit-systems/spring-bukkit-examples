package kr.summitsystems.chatlogger.domain

interface ChatLogRepository {
    suspend fun save(chatLog: ChatLog)
}