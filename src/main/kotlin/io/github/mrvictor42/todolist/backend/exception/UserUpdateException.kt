package io.github.mrvictor42.todolist.backend.exception

class UserUpdateException(username : String) : RuntimeException("Não Foi Possível Atualizar O Usuário $username") {
}