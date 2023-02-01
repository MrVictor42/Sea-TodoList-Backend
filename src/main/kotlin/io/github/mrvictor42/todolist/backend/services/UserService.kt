package io.github.mrvictor42.todolist.backend.services

import io.github.mrvictor42.todolist.backend.exception.CustomMessageException
import io.github.mrvictor42.todolist.backend.model.User
import io.github.mrvictor42.todolist.backend.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
@RequiredArgsConstructor
@Transactional
class UserService(
    private val userRepository: UserRepository,
) {

    @Throws(CustomMessageException::class)
    fun save(user: User) : User {
        val exists : Boolean = userRepository.existsByUsername(user.username)

        if(exists) {
            throw CustomMessageException("O Usuário ${ user.username } Já Foi Cadastrado!")
        } else {
//            user.password = passwordEncoder.encode(user.password)

            if(user.role == "" || user.role == null) {
                user.role = "ROLE_USER"
            } else {
                // Nothing to do
            }

            return userRepository.save(user)
        }
    }

    fun update(user: User) : User {
        val exists = userRepository.existsByUserId(user.userId)

        if(exists) {
            return userRepository.save(user)
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    @Throws(CustomMessageException::class)
    fun getCurrentUser(userId : Long) : User {
        val exists : Boolean = userRepository.existsByUserId(userId)

        if(exists) {
            return userRepository.findById(userId).orElseThrow()
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    fun countUser() : Long {
        return userRepository.count()
    }
    fun userList() : List<User> {
        return userRepository.findAll()
    }

    fun delete(userId: Long) {
        userRepository.findById(userId).map { user ->
            userRepository.deleteById(user.userId)
        }.orElseThrow()
    }
//
//    @Throws(ObjectAlreadyExistsException::class)
//    override fun loadUserByUsername(username: String): UserDetails {
//        val userExists : Boolean = userRepository.existsByUsername(username)
//
//        if(userExists) {
//            val user : User = userRepository.findByUsername(username)
//            val authorities : MutableList<SimpleGrantedAuthority> = mutableListOf()
//
//            authorities.add(SimpleGrantedAuthority("ROLE_USER"))
//
//            return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
//        } else {
//            throw UsernameNotFoundException("Usuário Não Encontrado")
//        }
//    }
}