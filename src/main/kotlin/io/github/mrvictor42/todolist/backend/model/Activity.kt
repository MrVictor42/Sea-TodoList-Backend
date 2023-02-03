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
    var title : String = ""
    @Column(nullable = false)
    @get: NotEmpty(message = "{description.required}")
    var description : String = ""
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User = User()
    @OneToMany(cascade = [CascadeType.REMOVE])
    val tasks : MutableList<Task> = mutableListOf()
}