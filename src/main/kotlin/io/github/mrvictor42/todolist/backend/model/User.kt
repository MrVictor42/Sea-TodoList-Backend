package io.github.mrvictor42.todolist.backend.model

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Entity
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val userId : Long = 0
    @Column(nullable = false, length = 120, unique = true)
    @get: NotEmpty(message = "{username.required}")
    val username : String = ""
    @Column(nullable = false)
    @get: NotEmpty(message = "{password.required}")
    @get: Length(min = 6, message = "{password.shortLength}")
    val password : String = ""
    @Column(nullable = false)
    @get: NotEmpty(message = "{name.required}")
    @get: Length(min = 3, message = "{name.shortLength}")
    var name : String = ""
    @Column(nullable = false, unique = true)
    @get: Email(message = "{email.invalid}")
    @get: NotBlank(message = "{email.required}")
    val email : String = ""
    var role : String? = null
}