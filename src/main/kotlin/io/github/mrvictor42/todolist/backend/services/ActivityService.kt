package io.github.mrvictor42.todolist.backend.services

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.Activity
import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.repository.ActivityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ActivityService(private val activityRepository : ActivityRepository) {

    fun save(activity: Activity) : Activity {
        return activityRepository.save(activity)
    }

    @Throws(CustomMessageException::class)
    fun update(activity: Activity) : Activity {
        val exists : Boolean = activityRepository.existsByActivityId(activity.activityId)

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
            return activityRepository.findByActivityId(activityId)
        } else {
            throw CustomMessageException("Atividade Não Encontrada")
        }
    }

    fun getActivityList(userId : Long) : List<Activity> {
        return activityRepository.findAllByUser_UserId(userId)
    }

    fun delete(activityId: Long) {
        activityRepository.findById(activityId).map { activity ->
            activityRepository.deleteById(activity.activityId)
        }
    }

    @Throws(CustomMessageException::class)
    fun existsActivity(activityId : Long) : Boolean {
        val exists : Boolean = activityRepository.existsById(activityId)

        return if(exists) {
            true
        } else {
            throw CustomMessageException("Atividade Não Encontrada")
        }
    }

    fun getActivityById(activityId : Long) : Activity {
        return activityRepository.findByActivityId(activityId)
    }
}