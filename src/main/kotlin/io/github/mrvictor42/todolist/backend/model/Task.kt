package io.github.mrvictor42.todolist.backend.model

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val taskId : Long = 0
    @Column(nullable = false)
    @get: NotEmpty(message = "{title.required}")
    var title : String = ""
    @NotNull
    var status : String = ""
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    var activity = Activity()
}