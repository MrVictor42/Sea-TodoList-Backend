package io.github.mrvictor42.todolist.backend.api

import io.github.mrvictor42.todolist.backend.dto.TaskDTO
import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.Activity
import io.github.mrvictor42.todolist.backend.model.Task
import io.github.mrvictor42.todolist.backend.services.ActivityService
import io.github.mrvictor42.todolist.backend.services.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/task")
class TaskController(private val taskService: TaskService, private val activityService: ActivityService) {

    @PostMapping("/save")
    fun save(@Valid @RequestBody taskDto : TaskDTO) {
        val existsActivity : Boolean = activityService.existsActivity(taskDto.activityId)

        if(existsActivity) {
            val task = Task()
            val activity : Activity = activityService.getActivityById(taskDto.activityId)

            try {
                 val uri = URI.create(
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/task/save").toUriString()
                 )

                task.activity = activity
                task.status = "Pendente"
                task.title = taskDto.title

                ResponseEntity.created(uri).body(taskService.save(task))
            } catch (exception : CustomMessageException) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, exception.message)
            }
        }
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody task : Task) : ResponseEntity<Task> {
        return try {
            ResponseEntity.ok().body(taskService.update(task))
        } catch (exception : CustomMessageException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping("/current_task/{taskId}")
    fun getCurrentTask(@PathVariable taskId : Long) : ResponseEntity<Task> {
        return try {
            ResponseEntity.ok().body(taskService.getCurrentTask(taskId))
        } catch (exception : CustomMessageException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping("/list/{taskId}")
    fun getTaskList(@PathVariable taskId : Long) : ResponseEntity<List<Task>> {
        return ResponseEntity.ok().body(taskService.getTaskList(taskId))
    }

    @DeleteMapping("/delete/{taskId}")
    fun delete(@PathVariable taskId : Long) : ResponseEntity<Any> {
        return try {
            taskService.delete(taskId)
            ResponseEntity.noContent().build()
        } catch (exception : CustomMessageException) {
            ResponseEntity.badRequest().body(null)
        }
    }
}