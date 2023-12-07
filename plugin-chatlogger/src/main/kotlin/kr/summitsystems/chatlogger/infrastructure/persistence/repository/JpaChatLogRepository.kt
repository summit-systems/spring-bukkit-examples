package kr.summitsystems.chatlogger.infrastructure.persistence.repository

import kr.summitsystems.chatlogger.domain.ChatLog
import org.springframework.data.jpa.repository.JpaRepository

interface JpaChatLogRepository : JpaRepository<ChatLog, Long>