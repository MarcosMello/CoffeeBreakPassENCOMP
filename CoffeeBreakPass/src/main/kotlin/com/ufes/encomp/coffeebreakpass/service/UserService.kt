package com.ufes.encomp.coffeebreakpass.service

import com.ufes.encomp.coffeebreakpass.entities.User
import com.ufes.encomp.coffeebreakpass.enums.defaultDuplicateMessage
import com.ufes.encomp.coffeebreakpass.exception.CustomEntityNotFoundException
import com.ufes.encomp.coffeebreakpass.exception.CustomUniqueConstraintsException
import com.ufes.encomp.coffeebreakpass.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){ //TODO:Password Validation
    fun getUniqueConstraintFields(username : String, email : String) : Pair<List<User>, List<User>>{
        return Pair(
            userRepository.findByUsername(username),
            userRepository.findByEmail(email)
        )
    }

    fun getAllUsers() : List<User> = userRepository.findAll()

    fun getUserByID(userID : Long) : User = userRepository.findById(userID)
            .orElseThrow{ CustomEntityNotFoundException("User") }

    fun create(user : User) : User {
        val isUnique : (Pair<List<User>, List<User>>) -> Boolean = {
            users : Pair<List<User>, List<User>> ->
                users.first.isEmpty() && users.second.isEmpty()
        }

        val uniqueConstraints : Pair<List<User>, List<User>> =
            getUniqueConstraintFields(user.username, user.email)

        return if (isUnique(uniqueConstraints)){
            userRepository.save(user)
        }
        else{
            val notUniqueMap : MutableMap<String, List<String>> = mutableMapOf()

            if (uniqueConstraints.first.isNotEmpty()) notUniqueMap["username"] =
                listOf(defaultDuplicateMessage("Username"))
            if (uniqueConstraints.second.isNotEmpty()) notUniqueMap["Email"] =
                listOf(defaultDuplicateMessage("Email"))

            throw CustomUniqueConstraintsException(notUniqueMap)
        }
    }

    fun update(userID : Long, user : User) : User {
        val existingUser = getUserByID(userID)

        val isUnique : (Pair<List<User>, List<User>>) -> Boolean = {
            users: Pair<List<User>, List<User>> ->
                (users.first.contains(existingUser) || users.first.isEmpty()) &&
                (users.second.contains(existingUser) || users.second.isEmpty())
        }

        val uniqueConstraints : Pair<List<User>, List<User>> =
            getUniqueConstraintFields(user.username, user.email)

        return if (isUnique(uniqueConstraints)) {
            existingUser.username = user.username
            existingUser.email = user.email
            existingUser.password = user.password ?: existingUser.password

            userRepository.save(existingUser)
        } else {
            val notUniqueMap : MutableMap<String, List<String>> = mutableMapOf()

            if (uniqueConstraints.first.isNotEmpty() && !uniqueConstraints
                    .first.contains(existingUser)
                ) notUniqueMap["username"] = listOf(defaultDuplicateMessage("Username"))
            if (uniqueConstraints.second.isNotEmpty() && !uniqueConstraints
                    .second.contains(existingUser)
                ) notUniqueMap["Email"] = listOf(defaultDuplicateMessage("Email"))

            throw CustomUniqueConstraintsException(notUniqueMap)
        }
    }

    fun delete(userID : Long) {
        return if (userRepository.existsById(userID)){
            userRepository.deleteById(userID)
        } else throw CustomEntityNotFoundException("User")
    }
}