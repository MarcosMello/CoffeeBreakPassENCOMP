package com.ufes.encomp.coffeebreakpass.service

import com.ufes.encomp.coffeebreakpass.entities.User
import com.ufes.encomp.coffeebreakpass.enums.defaultDuplicateMessage
import com.ufes.encomp.coffeebreakpass.exception.CustomEntityNotFoundException
import com.ufes.encomp.coffeebreakpass.exception.CustomUniqueConstraintsException
import com.ufes.encomp.coffeebreakpass.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){ //TODO: Logs and Password Validation
    fun getUniqueConstraintFields(email : String) : List<User> = userRepository
        .findByEmail(email)

    fun getAllUsers() : List<User> = userRepository.findAll()

    fun getUserByID(userID : Long) : User = userRepository.findById(userID)
            .orElseThrow{ CustomEntityNotFoundException("User") }

    fun create(user : User) : User {
        val isUnique : (List<User>) -> Boolean = {
            users : List<User> -> users.isEmpty()
        }

        val uniqueConstraints : List<User> =
            getUniqueConstraintFields(user.email)

        return if (isUnique(uniqueConstraints)){
            userRepository.save(user)
        }
        else{
            val notUniqueMap : MutableMap<String, List<String>> = mutableMapOf()

            if (uniqueConstraints.isNotEmpty()) notUniqueMap["Email"] =
                listOf(defaultDuplicateMessage("Email"))

            throw CustomUniqueConstraintsException(notUniqueMap)
        }
    }

    fun update(userID : Long, user : User) : User {
        val existingUser = getUserByID(userID)

        val isUnique : (List<User>) -> Boolean = {
            users: List<User> ->
                (users.contains(existingUser) || users.isEmpty())
        }

        val uniqueConstraints : List<User> =
            getUniqueConstraintFields(user.email)

        return if (isUnique(uniqueConstraints)) {
            existingUser.name = user.name
            existingUser.email = user.email
            //existingUser.password = user.password ?: existingUser.password

            userRepository.save(existingUser)
        } else {
            val notUniqueMap : MutableMap<String, List<String>> = mutableMapOf()

            if (uniqueConstraints.isNotEmpty() && !uniqueConstraints.contains(existingUser))
                notUniqueMap["Email"] = listOf(defaultDuplicateMessage("Email"))

            throw CustomUniqueConstraintsException(notUniqueMap)
        }
    }

    fun delete(userID : Long) {
        return if (userRepository.existsById(userID)){
            userRepository.deleteById(userID)
        } else throw CustomEntityNotFoundException("User")
    }
}