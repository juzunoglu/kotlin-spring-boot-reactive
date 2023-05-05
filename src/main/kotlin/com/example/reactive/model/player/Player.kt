package com.example.reactive.model.player

import io.r2dbc.spi.Row
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.ZonedDateTime
import java.util.*

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
) {
    companion object {
        fun fromRow(row: Row): Player {
            return Player(
                id = row.get("id", Long::class.java)!!,
                playerUid = row.get("player_uid", String::class.java)!!,
                deviceId = row.get("device_id", String::class.java)!!,
                playerName = row.get("player_name", String::class.java)!!,
                status = row.get("status", Int::class.java)!!,
                firstLogin = row.get("first_login", ZonedDateTime::class.java)!!,
                lastLogin = row.get("last_login", ZonedDateTime::class.java),
                lastCollectedTime = row.get("last_collected_time", ZonedDateTime::class.java),
                playerEmail = row.get("player_email", String::class.java),
                notificationToken = row.get("notification_token", String::class.java),
                linkId = row.get("link_id", String::class.java)
            )
        }
    }
}
