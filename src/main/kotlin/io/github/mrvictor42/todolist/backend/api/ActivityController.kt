package io.github.mrvictor42.todolist.backend.api

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.Activity
import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.dto.ActivityDTO
import io.github.mrvictor42.todolist.backend.services.ActivityService
import io.github.mrvictor42.todolist.backend.services.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/activity")
class ActivityController(private val activityService: ActivityService, private val userService : UserService) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/save")
    fun save(@Valid @RequestBody activityDto : ActivityDTO) {
        val existsUser: Boolean = userService.existsUser(activityDto.userId)

        if (existsUser) {
            val activity = Activity()
            val user: User = userService.getUserById(activityDto.userId)

            try {
                val uri = URI.create(
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/activity/save").toUriString()
                )

                activity.user = user
                activity.title = activityDto.title
                activity.description = activityDto.description
                activity.status = "Pendente"

                ResponseEntity.created(uri).body(activityService.save(activity))
            } catch (exception : CustomMessageException) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, exception.message)
            }
        }
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody activity: Activity) : ResponseEntity<Activity> {
        return try {
            ResponseEntity.ok().body(activityService.update(activity))
        } catch (runtimeException : RuntimeException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping("/current_activity/{activityId}")
    fun getCurrentActivity(@PathVariable activityId : Long) : ResponseEntity<Activity> {
        return try {
            ResponseEntity.ok().body(activityService.getCurrentActivity(activityId))
        } catch (runtime : RuntimeException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping("/list/{userId}")
    fun getActivityList(@PathVariable userId : Long) : ResponseEntity<List<Activity>> {
        return ResponseEntity.ok().body(activityService.getActivityList(userId))
    }

    @DeleteMapping("/delete/{activityId}")
    fun delete(@PathVariable activityId : Long) : ResponseEntity<Any> {
        return try {
            activityService.delete(activityId)
            ResponseEntity.noContent().build()
        } catch (runtimeException : RuntimeException) {
            ResponseEntity.badRequest().body(null)
        }
    }
}