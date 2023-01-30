package com.sea.todolist.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val userId : Long = 0
    @Column(nullable = false, length = 60, unique = true)
    @get: NotEmpty(message = "{name.required}")
    @get: Length(min = 3, message = "{name.shortLength}")
    val name : String = ""
    @Column(nullable = false, unique = true)
    @get: Email(message = "{email.invalid}")
    @get: NotBlank(message = "{email.required}")
    var email : String = "email@email.com"
    @Lob
    var photo: ByteArray? = null
}