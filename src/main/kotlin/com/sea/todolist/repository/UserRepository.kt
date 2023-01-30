package com.sea.todolist.repository

import com.sea.todolist.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByName(name : String) : Boolean
    fun findByName(name: String) : User
}