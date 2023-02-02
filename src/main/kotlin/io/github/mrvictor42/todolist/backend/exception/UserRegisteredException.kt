package io.github.mrvictor42.todolist.backend.exception

class UserRegisteredException(login: String) : RuntimeException("Usuário Já Cadastrado Para o Login $login")