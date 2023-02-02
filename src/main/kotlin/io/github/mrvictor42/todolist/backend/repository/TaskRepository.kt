package io.github.mrvictor42.todolist.backend.repository

import io.github.mrvictor42.todolist.backend.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {
    fun findAllByActivity_ActivityId(activityId : Long) : List<Task>
    fun existsByTaskId(taskId : Long) : Boolean
}