package io.github.mrvictor.todolist.repository

import io.github.mrvictor.todolist.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username : String) : User
    fun existsByUsername(username: String) : Boolean
    fun findByUserId(userId : Long) : User
}