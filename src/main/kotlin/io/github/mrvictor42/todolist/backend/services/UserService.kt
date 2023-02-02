package io.github.mrvictor42.todolist.backend.services

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserDetailsService {

    @Throws(CustomMessageException::class)
    fun save(user: User) : User {
        val exists : Boolean = userRepository.existsByUsername(user.username)

        if(exists) {
            throw CustomMessageException("O Usuário ${ user.username } Já Foi Cadastrado!")
        } else {
            user.password = passwordEncoder.encode(user.password)

            if(user.role == "" || user.role == null) {
                user.role = "ROLE_USER"
            } else {
                // Nothing to do
            }
            return userRepository.save(user)
        }
    }

    @Throws(CustomMessageException::class)
    fun update(user: User) : User {
        val exists = userRepository.existsByUsername(user.username)

        if(exists) {
            return userRepository.save(user)
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    @Throws(CustomMessageException::class)
    fun getCurrentUser(username: String) : User {
        val exists : Boolean = userRepository.existsByUsername(username)

        if(exists) {
            return userRepository.findByUsername(username)
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    fun userList() : List<User> {
        return userRepository.findAll()
    }

    fun delete(userId: Long) {
        userRepository.findById(userId).map { user ->
            userRepository.deleteById(user.userId)
        }.orElseThrow {
            throw CustomMessageException("Erro ao Deletar o Usuário, Tente Novamente!")
        }
    }

    @Throws(CustomMessageException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val exists : Boolean = userRepository.existsByUsername(username)

        if(exists) {
            val user : User = userRepository.findByUsername(username)
            val authorities : MutableList<SimpleGrantedAuthority> = mutableListOf()

            authorities.add(SimpleGrantedAuthority("ROLE_USER"))

            return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    fun countUser() : Long {
        return userRepository.count()
    }
}