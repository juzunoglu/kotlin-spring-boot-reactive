package com.example.reactive.repository

import com.example.reactive.model.player.PlayerVault
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PlayerVaultRepository : CoroutineCrudRepository<PlayerVault, Long> {

    @Query("select * from kf.player_vault where player_id = :playerId")
    fun findByPlayerId(@Param("playerId") playerId: Long): Flow<PlayerVault>
}