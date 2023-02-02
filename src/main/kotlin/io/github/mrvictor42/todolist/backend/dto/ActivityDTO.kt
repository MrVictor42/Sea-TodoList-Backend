package io.github.mrvictor42.todolist.backend.dto

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class ActivityDTO {
    @Column(nullable = false)
    @get: NotEmpty(message = "{title.required}")
    val title : String = ""
    @Column(nullable = false)
    @get: NotEmpty(message = "{description.required}")
    val description : String = ""
    @NotNull(message = "{user.required}")
    val userId : Long = 0
}