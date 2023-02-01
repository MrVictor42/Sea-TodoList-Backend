package io.github.mrvictor42.todolist.backend.services

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.Activity
import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.repository.ActivityRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional
class ActivityService(private val activityRepository : ActivityRepository) {

    @Throws(CustomMessageException::class)
    fun save(activity: Activity) : Activity {
        return activityRepository.save(activity)
    }

    fun update(activity: Activity) : Activity {
        val exists = activityRepository.existsByActivityId(activity.activityId)

        if(exists) {
            return activityRepository.save(activity)
        } else {
            throw CustomMessageException("Atividade Não Encontrada")
        }
    }

    @Throws(CustomMessageException::class)
    fun getCurrentActivity(activityId : Long) : Activity {
        val exists : Boolean = activityRepository.existsByActivityId(activityId)

        if(exists) {
            return activityRepository.findById(activityId).orElseThrow()
        } else {
            throw CustomMessageException("Atividade Não Encontrada")
        }
    }

    fun getActivityList() : List<Activity> {
        return activityRepository.findAll()
    }

    fun delete(activityId: Long) {
        activityRepository.findById(activityId).map { activity -> {
            activityRepository.deleteById(activity.activityId)
        }}.orElseThrow()
    }
}