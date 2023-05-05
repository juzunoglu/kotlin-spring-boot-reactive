package com.example.reactive.repository

import com.example.reactive.model.player.PlayerCharacter
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
@Suppress("SpringDataRepositoryMethodReturnTypeInspection")
interface PlayerCharacterRepository : CoroutineCrudRepository<PlayerCharacter, Long> {
    @Query(
        """
        select * from kf.player_char_roster where player_id = :playerId and char_id = :charId
        """)
    suspend fun findByPlayerIdAndCharId(@Param("playerId") playerId: Long, @Param("charId") charId: Long): PlayerCharacter?

}