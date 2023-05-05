package com.example.reactive.service

import com.example.reactive.model.player.Player
import com.example.reactive.model.player.PlayerCharacter
import com.example.reactive.model.player.PlayerVault
import com.example.reactive.repository.PlayerCharacterRepository
import com.example.reactive.repository.PlayerRepository
import com.example.reactive.repository.PlayerVaultRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerRepository: PlayerRepository,
    private val playerCharacterRepository: PlayerCharacterRepository,
    private val playerVaultRepository: PlayerVaultRepository,
) {
    suspend fun fetchPlayerAndCharacterAndVault(playerUid: String, charId: Long): PlayerAndCharacterAndVault {
        val player: Player = playerRepository.findByPlayerUid(playerUid)
            ?: throw NoSuchElementException("No player exists $playerUid")

        val playerCharacter = withContext(Default) {
            println("Running ${coroutineContext[CoroutineName]} on thread: ${Thread.currentThread().name}")
            playerCharacterRepository.findByPlayerIdAndCharId(player.id, charId)
                ?: throw NoSuchElementException("No character exists $charId")
        }
        val playerVaults = withContext(Default) {
            println("Running ${coroutineContext[CoroutineName]} on thread: ${Thread.currentThread().name}")
            playerVaultRepository.findByPlayerId(playerId = player.id).toList()
        }
        return PlayerAndCharacterAndVault(player, playerCharacter, playerVaults)
    }

    data class PlayerAndCharacterAndVault(
        val player: Player,
        val character: PlayerCharacter,
        val vault: List<PlayerVault>,
    )
}

