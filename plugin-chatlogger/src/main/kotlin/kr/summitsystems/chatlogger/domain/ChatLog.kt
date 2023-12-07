package kr.summitsystems.chatlogger.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "springbukkitexamples_chatlog",
    indexes = [Index(columnList = "player_id")]
)
class ChatLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "player_id")
    val playerId: UUID,

    @Column(name = "content")
    val content: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now() // just for test...
)