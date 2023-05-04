package com.example.reactive.model.player

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "player_vault", schema = "kf")
data class PlayerVault(

        @Id
        @Column("id")
        val id: Long,

        @Column("player_id")
        val playerId: Long,

        @Column("player_uid")
        val playerUid: String,

        @Column("currency_type")
        val currencyType: Int,

        @Column("currency_value")
        val currencyValue: Double,

        @Column("visibility")
        val visibility: String,

        @Column("ext_data")
        val extData: String? = null
)
