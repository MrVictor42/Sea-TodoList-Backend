package io.github.mrvictor.todolist.dto

import javax.persistence.Column
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class TaskDTO {
    @Column(nullable = false)
    @get: NotEmpty(message = "{title.required}")
    val title : String = ""
    val status : String = ""
    @NotNull(message = "{user.required}")
    val activityId : Long = 0
}