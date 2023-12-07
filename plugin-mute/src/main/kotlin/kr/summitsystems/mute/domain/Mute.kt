package kr.summitsystems.mute.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(
    name = "springbukkitexamples_mute",
    indexes = [Index(columnList = "player_id")]
)
class Mute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "player_id")
    val playerId: UUID,

    @Column(name = "expires_at")
    val expiresAt: LocalDateTime
)