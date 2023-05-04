package com.example.reactive.model.player

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime

@Table(name = "player", schema = "kf")
data class Player(
        @Id
        @Column("id")
        val id: Long,
        @Column("player_uid")
        val playerUid: String,

        @Column("device_id")
        val deviceId: String,

        @Column("player_name")
        val playerName: String,

        @Column("status")
        val status: Int,

        @Column("first_login")
        val firstLogin: ZonedDateTime,

        @Column("last_login")
        val lastLogin: ZonedDateTime? = null,

        @Column("last_collected_time")
        val lastCollectedTime: ZonedDateTime? = null,

        @Column("player_email")
        val playerEmail: String? = null,

        @Column("notification_token")
        val notificationToken: String? = null,

        @Column("link_id")
        val linkId: String? = null
)
