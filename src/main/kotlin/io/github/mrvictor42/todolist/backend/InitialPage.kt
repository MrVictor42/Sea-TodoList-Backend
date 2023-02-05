package io.github.mrvictor42.todolist.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InitialPage {

    @GetMapping("/")
    fun initialPage() : String {
        return "Initial Page"
    }
}