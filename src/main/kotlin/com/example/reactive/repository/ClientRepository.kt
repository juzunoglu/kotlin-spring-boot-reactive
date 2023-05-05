package com.example.reactive.repository

import com.example.reactive.model.player.Player
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitSingleOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.reactive.TransactionalOperator


@Repository
class ClientRepository(
    private val client: DatabaseClient,
    private val operator: TransactionalOperator,
) {

    suspend fun findOne(playerId: Long): Player {
        return client.sql("SELECT * from kf.player where id =: playerId")
            .bind("playerId", playerId)
            .map { row, _ -> Player.fromRow(row) }
            .awaitSingleOrNull() ?: throw NoSuchElementException("No Player found with id $playerId")
    }

}