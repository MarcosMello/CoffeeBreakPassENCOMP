package com.ufes.encomp.coffeebreakpass.entities

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.Instant

@Entity
@Table(name = "attendees_to_events")
data class AttendeeToEvent(
        @Id
        @ManyToOne
        @JoinColumn(name = "atendee_id")
        var attendee : Attendee,

        @Id
        @ManyToOne
        @JoinColumn(name = "event_id")
        var event : Event,

        @Column(nullable = false)
        var isPaid : Boolean = false,

        @Column(nullable = false)
        var isCoffeeBreakAvailable : Boolean = false,

        @CreatedDate
        var createdDate: Instant,
)
