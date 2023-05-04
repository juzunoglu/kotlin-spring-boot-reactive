package com.example.reactive.handler

import com.example.reactive.service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PlayerController(val playerService: PlayerService) {

    @GetMapping(value = ["/api/v1/{playerUid}/player"])
    suspend fun findPlayer(@PathVariable playerUid: String): ResponseEntity<Any> {
        val result = playerService.findPlayerById(playerUid)
        return result.fold(
                { error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to error.message)) },
                { player -> ResponseEntity.ok().body(player) }
        )
    }

    @GetMapping(value = ["/api/v1/{playerUid}/{charId}/player-character"])
    suspend fun findPlayerAndCharacter(@PathVariable playerUid: String, @PathVariable charId: Long): ResponseEntity<PlayerService.PlayerAndItsCharacter> {
        val result = playerService.combinePlayerAndPlayerChar2(playerUid, charId)
        return ResponseEntity.ok().body(result)
    }

    @GetMapping(value = ["/api/v1/{playerUid}/{charId}/player-character-vault-concurrent"])
    suspend fun findPlayerAndCharacterAndVaultConcurrent(@PathVariable playerUid: String, @PathVariable charId: Long): ResponseEntity<Any> {
        val result = playerService.combinePlayerAndPlayerCharAndVaultConcurrent(playerUid, charId)
        return ResponseEntity.ok().body("OK")
    }

    @GetMapping(value = ["/api/v1/{playerUid}/{charId}/player-character-vault"])
    suspend fun findPlayerAndCharacterAndVault(@PathVariable playerUid: String, @PathVariable charId: Long): ResponseEntity<Any> {
        val result = playerService.combinePlayerAndPlayerCharAndVault(playerUid, charId)
        return ResponseEntity.ok().body("OK")
    }




}