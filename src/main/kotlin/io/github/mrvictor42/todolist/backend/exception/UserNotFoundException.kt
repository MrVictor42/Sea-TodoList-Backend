package io.github.mrvictor42.todolist.backend.exception

class UserNotFoundException(message: String) : RuntimeException("O Usuário $message Não Foi Encontrado")