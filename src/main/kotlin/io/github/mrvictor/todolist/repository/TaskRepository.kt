package io.github.mrvictor.todolist.repository

import io.github.mrvictor.todolist.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {
    fun findAllByActivity_ActivityId(activityId : Long) : List<Task>
    fun existsByTaskId(taskId : Long) : Boolean
    fun findByTaskId(taskId : Long) : Task
}