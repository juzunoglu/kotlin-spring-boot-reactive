package com.example.reactive.router

import com.example.reactive.handler.PlayerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @Bean
    fun playerRouter(handler: PlayerHandler) = coRouter {
        GET("/api/v1/{playerUid}/{charId}/fetch", handler::findPlayerAndCharacter)
    }
}