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
    var status : String = ""
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "user_id", nullable = false)
    var user : User = User()
}