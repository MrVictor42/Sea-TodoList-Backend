package io.github.mrvictor.todolist.repository

import io.github.mrvictor.todolist.model.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository : JpaRepository<Activity, Long> {
    fun existsByActivityId(activityId : Long) : Boolean
    fun findAllByUser_UserId(userId : Long) : List<Activity>
    fun findByActivityId(activityId : Long) : Activity
}