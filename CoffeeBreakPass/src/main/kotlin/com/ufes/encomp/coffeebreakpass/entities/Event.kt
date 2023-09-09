package com.ufes.encomp.coffeebreakpass.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.Date

@Entity
@Table(name = "events")
data class Event(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Name field field must be between 3 and 255 characters")
        @Column(nullable = false)
        var name : String,

        @field:NotNull
        @field:NotBlank
        @Column(nullable = false)
        var date: Date,

        @OneToMany(mappedBy = "event", cascade = [(CascadeType.ALL)])
        var attendees : Set<AttendeeToEvent>,

        @CreatedDate
        var createdDate: Instant,

        @LastModifiedDate
        var modifiedDate: Instant,
)
