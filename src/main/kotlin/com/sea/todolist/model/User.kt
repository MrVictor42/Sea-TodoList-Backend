package com.sea.todolist.model

import jakarta.persistence.*
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
    @Lob
    var photo: ByteArray? = null
}