package com.sea.todolist.controller

import com.sea.todolist.model.User
import com.sea.todolist.services.UserService
import jakarta.servlet.http.Part
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping("/save")
    fun save(@Valid @RequestBody user : User) : ResponseEntity<User> {
        return try {
            val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString())
            ResponseEntity.created(uri).body(userService.save(user))
        } catch (runtime : RuntimeException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping("/list")
    fun getUserList() : ResponseEntity<List<User>> {
        return ResponseEntity.ok().body(userService.userList())
    }

    @PutMapping("/update")
    fun updateUser(@Valid @RequestBody user : User) : ResponseEntity<User> {
        return try {
            ResponseEntity.ok().body(userService.update(user))
        } catch (runtimeException : RuntimeException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @DeleteMapping("/delete/{userId}")
    fun deleteUser(@PathVariable userId : Long) {
        userService.delete(userId)
    }
}