package com.ufes.encomp.coffeebreakpass.repository

import com.ufes.encomp.coffeebreakpass.entities.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long> {
}