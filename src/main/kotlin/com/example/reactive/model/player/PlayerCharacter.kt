package com.example.reactive.model.player

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table(name = "player_char_roster", schema = "kf")
data class PlayerCharacter(

        @Id
        @Column("id")
        val id: Long? = null,

        @Column("player_id")
        val playerId: Long,

        @Column("player_uid")
        val playerUid: String,

        @Column("char_id")
        val charId: Long,

        @Column("level_code")
        val levelCode: Int,

        @Column("unlock_status")
        val lockStatus: String,

        @Column("scheme_id")
        val schemeId: Long? = null,

        @Column("amount")
        val amount: Int,

        @Column("char_rank")
        val charRank: Int
)