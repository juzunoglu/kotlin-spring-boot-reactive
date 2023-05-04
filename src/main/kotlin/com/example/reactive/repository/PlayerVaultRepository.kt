package com.example.reactive.repository

import com.example.reactive.model.player.PlayerVault
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface PlayerVaultRepository: ReactiveCrudRepository<PlayerVault, Long> {

    fun findByPlayerId(playerId: Long): Flux<PlayerVault>
}