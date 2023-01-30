package com.sea.todolist.services

import com.sea.todolist.model.User
import com.sea.todolist.repository.UserRepository
import exceptions.CustomMessageException
import jakarta.servlet.http.Part
import jakarta.transaction.Transactional
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.stereotype.Service
import java.io.IOException

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    @Throws(CustomMessageException::class)
    fun save(user: User, photo: Part?) : User {
        val exists : Boolean = userRepository.existsByName(user.name)

        if(exists) {
            throw CustomMessageException("O Usuário ${ user.name } Já Foi Cadastrado!")
        } else {
            if(photo != null) {
                try {
                    val userSaved = userRepository.save(user)
                    val inputStream = photo.inputStream
                    val bytes = ByteArray(photo.size.toInt())

                    IOUtils.readFully(inputStream, bytes)
                    userSaved.photo = bytes

                    return userRepository.save(userSaved)
                } catch (e: IOException) {
                    throw CustomMessageException("Não Foi Possível Salvar a Imagem de Usuário")
                }
            } else {
                return userRepository.save(user)
            }
        }
    }

    fun userList() : List<User> {
        return userRepository.findAll()
    }

    fun countUser() : Long {
        return userRepository.count()
    }

    @Throws(CustomMessageException::class)
    fun getCurrentUser(name: String) : User {
        val exists : Boolean = userRepository.existsByName(name)

        if(exists) {
            return userRepository.findByName(name)
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    fun update(user: User) : User {
        val existsUser = userRepository.existsByName(user.name)

        if(existsUser) {
            return userRepository.save(user)
        } else {
            throw CustomMessageException("Usuário Não Encontrado")
        }
    }

    fun delete(userId : Long) : Unit {
        userRepository.deleteById(userId)
    }
}