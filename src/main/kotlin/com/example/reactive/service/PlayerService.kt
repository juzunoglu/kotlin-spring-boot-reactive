package com.example.reactive.service

import arrow.core.Either
import com.example.reactive.model.player.Player
import com.example.reactive.model.player.PlayerCharacter
import com.example.reactive.model.player.PlayerVault
import com.example.reactive.repository.PlayerCharacterRepository
import com.example.reactive.repository.PlayerRepository
import com.example.reactive.repository.PlayerVaultRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis


@Service
class PlayerService(
        private val playerRepository: PlayerRepository,
        private val playerCharacterRepository: PlayerCharacterRepository,
        private val playerVaultRepository: PlayerVaultRepository,
) {

    suspend fun findPlayerById(playerUid: String): Either<Error, Player> {
        playerRepository.findByPlayerUid(playerUid).awaitFirstOrNull()
                ?.let {
                    return Either.Right(it);
                } ?: return Either.Left(Error("No player found with this uuid: $playerUid"))
    }

    suspend fun findPlayerCharacter(playerId: Long, charId: Long): Either<Error, PlayerCharacter> {
        playerCharacterRepository.findByPlayerIdAndCharId(playerId = playerId, charId = charId).awaitFirstOrNull()
                ?.let {
                    return Either.Right(it)
                } ?: return Either.Left(Error("No player character found with this id: $charId"))
    }

    suspend fun combinePlayerAndPlayerChar2(playerUid: String, charId: Long): PlayerAndItsCharacter {
        return playerRepository.findByPlayerUid(playerUid)
                .flatMap { player ->
                    playerCharacterRepository.findByPlayerIdAndCharId(player.id, charId)
                            .map { PlayerAndItsCharacter(player, it) }

                }
                .awaitSingle()
    }

    suspend fun combinePlayerAndPlayerCharAndVaultConcurrent(playerUid: String, charId: Long) {
        val elapsed = measureTimeMillis {

            val player = playerRepository.findByPlayerUid(playerUid).awaitSingle()
            val playerCharAndVault = playerCharacterRepository.findByPlayerIdAndCharId(player.id, charId)
                    .zipWith(playerVaultRepository.findByPlayerId(player.id).collectList()).awaitSingle();
            val playerChar: PlayerCharacter = playerCharAndVault.t1
            val playerVaults: List<PlayerVault> = playerCharAndVault.t2
        }
        println(elapsed)
///       return PlayerAndCharacterAndVault(player, playerChar, playerVaults)
    }

    suspend fun combinePlayerAndPlayerCharAndVault2(playerUid: String, charId: Long) {
        val elapsed = measureTimeMillis {

            val player = playerRepository.findByPlayerUid(playerUid).awaitSingle()
            val playerCharAndVault = playerCharacterRepository.findByPlayerIdAndCharId(player.id, charId).awaitSingle()
            val playerVaults = playerVaultRepository.findByPlayerId(player.id).collectList().awaitSingle();
        }
        println(elapsed)
    }



    data class PlayerAndItsCharacter(
            val player: Player,
            val character: PlayerCharacter,
    )


    data class PlayerAndCharacterAndVault(
            val player: Player,
            val character: PlayerCharacter,
            val vault: List<PlayerVault>,
    )
    data class PlayerCharAndVault(
            val character: PlayerCharacter,
            val vault: List<PlayerVault>,
    )
}