package com.example.reactive.repository;

import com.example.reactive.model.player.PlayerCharacter
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface PlayerCharacterRepository : ReactiveCrudRepository<PlayerCharacter, Long> {

    fun findByPlayerIdAndCharId(playerId: Long, charId: Long): Mono<PlayerCharacter>

}