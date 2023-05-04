package com.example.reactive.handler

import com.example.reactive.service.PlayerService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class PlayerHandler(private val playerService: PlayerService) {

    suspend fun findPlayerAndCharacter(request: ServerRequest): ServerResponse {
        val playerUid = request.pathVariable("playerUid")
        val charId = request.pathVariable("charId").toLong()
        val result = playerService.fetchPlayerAndCharacterAndVault(playerUid, charId)
        return ServerResponse.ok().bodyValueAndAwait(result)
    }
}