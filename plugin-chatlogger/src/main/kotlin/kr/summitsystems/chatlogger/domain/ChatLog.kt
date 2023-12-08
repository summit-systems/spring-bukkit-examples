package kr.summitsystems.chatlogger.domain

import jakarta.persistence.*
import kr.summitsystems.springbukkit.jpa.type.LocationConverter
import org.bukkit.Location
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

    @Convert(converter = LocationConverter::class)
    @Column(name = "location")
    val location: Location,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now() // just for test...
)