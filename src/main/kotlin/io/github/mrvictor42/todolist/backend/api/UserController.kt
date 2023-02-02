package io.github.mrvictor42.todolist.backend.api

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val userService : UserService) {

    @PostMapping("/save")
    fun save(@Valid @RequestBody user : User) : ResponseEntity<User> {
        return try {
            val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString())
            ResponseEntity.created(uri).body(userService.save(user))
        } catch (exception : CustomMessageException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @GetMapping("/list")
    fun getUserList() : ResponseEntity<List<User>> {
        return ResponseEntity.ok().body(userService.userList())
    }

    @GetMapping("/current_user")
    fun getCurrentUser(@RequestParam("username") username : String) : ResponseEntity<User> {
        return try {
            ResponseEntity.ok().body(userService.getCurrentUser(username))
        } catch (exception : CustomMessageException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, exception.message)
        }
    }

    @PutMapping("/update")
    fun updateUser(@Valid @RequestBody user : User) : ResponseEntity<User> {
        return try {
            ResponseEntity.ok().body(userService.update(user))
        } catch (exception : CustomMessageException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @DeleteMapping("/delete/{userId}")
    fun deleteUser(@PathVariable userId : Long) : ResponseEntity<Any> {
        return try {
            userService.delete(userId)
            ResponseEntity.noContent().build()
        } catch (runtimeException : CustomMessageException) {
            ResponseEntity.badRequest().body(null)
        }
    }
}