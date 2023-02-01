package io.github.mrvictor42.todolist.backend.model

import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity

class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val activityId : Long = 0
    @Column(nullable = false)
    @get: NotEmpty(message = "{title.required}")
    val title : String = ""
    @Column(nullable = false)
    @get: NotEmpty(message = "{description.required}")
    val description : String = ""
}