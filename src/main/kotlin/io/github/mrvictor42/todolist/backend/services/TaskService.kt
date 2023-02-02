package io.github.mrvictor42.todolist.backend.services

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.Task
import io.github.mrvictor42.todolist.backend.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TaskService(private val taskRepository : TaskRepository) {

    fun save(task : Task) : Task {
        return taskRepository.save(task)
    }

    @Throws(CustomMessageException::class)
    fun update(task : Task) : Task {
        val exists : Boolean = taskRepository.existsByTaskId(task.taskId)

        if(exists) {
            return taskRepository.save(task)
        } else {
            throw CustomMessageException("Atividade Não Encontrada")
        }
    }

    @Throws(CustomMessageException::class)
    fun getCurrentTask(taskId : Long) : Task {
        val exists : Boolean = taskRepository.existsByTaskId(taskId)

        if(exists) {
            return taskRepository.findById(taskId).orElseThrow()
        } else {
            throw CustomMessageException("Atividade Não Encontrada")
        }
    }

    fun getTaskList(activityId : Long) : List<Task> {
        return taskRepository.findAllByActivity_ActivityId(activityId)
    }

    fun delete(taskId: Long) {
        taskRepository.findById(taskId).map { task ->
            taskRepository.deleteById(task.taskId)
        }.orElseThrow()
    }
}