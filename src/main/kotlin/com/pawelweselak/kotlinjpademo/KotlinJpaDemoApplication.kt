package com.pawelweselak.kotlinjpademo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories("com.pawelweselak.kotlinjpademo.repositories")
class KotlinJpaDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinJpaDemoApplication>(*args)
}
