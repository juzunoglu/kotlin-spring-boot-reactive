package com.example.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
class ReactiveApplication

fun main(args: Array<String>) {
	runApplication<ReactiveApplication>(*args)
}
