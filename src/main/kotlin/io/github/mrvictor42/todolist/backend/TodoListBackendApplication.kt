package io.github.mrvictor42.todolist.backend

import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.services.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class TodoListBackendApplication {

	@Value("\${security.jwt.signing-key}")
	val secretKey : String? = null

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

		user.username = "admin"
		user.password = "Bgatahkei42@"
		user.name = "Admin"
		user.email = "admin@gmail.com"

		userService.save(user)
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(TodoListBackendApplication::class.java, *args)
		}
	}
}