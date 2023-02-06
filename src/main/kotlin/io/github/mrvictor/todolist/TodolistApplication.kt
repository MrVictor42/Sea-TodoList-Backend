package io.github.mrvictor.todolist

import io.github.mrvictor.todolist.model.User
import io.github.mrvictor.todolist.services.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class TodolistApplication {

	@Bean
	fun passwordEncoder() : BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}

	@Bean
	fun run(userService: UserService): CommandLineRunner? {
		return CommandLineRunner {
			if(userService.countUser() == 0.toLong()) {
				populateDb(userService)
			} else {
				// Nothing to do
			}
		}
	}

	private fun populateDb(userService : UserService) {
		val user = User()

		user.username = "convidado"
		user.password = "convidado42"
		user.name = "Admin"
		user.email = "admin@gmail.com"

		userService.save(user)
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(TodolistApplication::class.java, *args)
		}
	}
}