package io.github.mrvictor42.todolist.backend

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TodoListBackendApplication {

	@Value("\${security.jwt.signing-key}")
	val secretKey : String? = null

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(TodoListBackendApplication::class.java, *args)
		}
	}
}