package com.ufes.encomp.coffeebreakpass.repository

import com.ufes.encomp.coffeebreakpass.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email : String) : List<User>
    fun findByUsername(username : String) : List<User>
}