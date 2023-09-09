package com.ufes.encomp.coffeebreakpass.repository

import com.ufes.encomp.coffeebreakpass.entities.Log
import org.springframework.data.jpa.repository.JpaRepository

interface LogInterface : JpaRepository<Log, Long> {
}