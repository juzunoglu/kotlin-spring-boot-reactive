package com.example.reactive.repository;

import com.example.reactive.model.player.Player
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface PlayerRepository : ReactiveCrudRepository<Player, Long> {

    fun findByPlayerUid(playerUid: String): Mono<Player>
}