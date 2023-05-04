package com.example.reactive.repository

import com.example.reactive.model.player.Player
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : CoroutineCrudRepository<Player, Long> {
    @Query("select * from kf.player where player_uid = :playerUid")
    fun findByPlayerUid(@Param("playerUid") playerUid: String): Flow<Player>
}