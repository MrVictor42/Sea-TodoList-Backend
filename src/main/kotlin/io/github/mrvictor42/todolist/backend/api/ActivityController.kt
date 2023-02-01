package io.github.mrvictor42.todolist.backend.api

import io.github.mrvictor42.todolist.backend.model.Activity
import io.github.mrvictor42.todolist.backend.services.ActivityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/activity")
class ActivityController(private val activityService: ActivityService) {

    @PostMapping("/save")
    fun save(@Valid @RequestBody activity : Activity) : ResponseEntity<Activity> {
        return try {
            val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/activity/save").toUriString())
            ResponseEntity.created(uri).body(activityService.save(activity))
        } catch (runtime : RuntimeException) {
            ResponseEntity.badRequest().body(null)
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

    @GetMapping("/list")
    fun getActivityList() : ResponseEntity<List<Activity>> {
        return ResponseEntity.ok().body(activityService.getActivityList())
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