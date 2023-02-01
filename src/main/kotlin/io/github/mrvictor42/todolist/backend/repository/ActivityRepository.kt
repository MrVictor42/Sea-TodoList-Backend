package io.github.mrvictor42.todolist.backend.repository

import io.github.mrvictor42.todolist.backend.model.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository : JpaRepository<Activity, Long> {
    fun existsByActivityId(activityId : Long) : Boolean
}