package com.ufes.encomp.coffeebreakpass.repository

import com.ufes.encomp.coffeebreakpass.entities.Attendee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AttendeeRepository : JpaRepository<Attendee, UUID> {
}