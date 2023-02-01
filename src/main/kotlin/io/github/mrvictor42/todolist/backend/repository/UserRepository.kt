package io.github.mrvictor42.todolist.backend.repository

import io.github.mrvictor42.todolist.backend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserId(userId : Long) : User
    fun existsByUserId(userId : Long) : Boolean
    fun existsByUsername(username: String) : Boolean
}