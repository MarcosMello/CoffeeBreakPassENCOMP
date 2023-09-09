package com.ufes.encomp.coffeebreakpass.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "attendees", uniqueConstraints = [
        UniqueConstraint(name = "email", columnNames = ["email"]),
        UniqueConstraint(name = "registrationNumber", columnNames = ["registrationNumber"])
])
data class Attendee(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val id : UUID,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Name field field must be between 3 and 255 characters")
        @Column(nullable = false)
        var name : String,

        @field:Email
        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Email field field must be between 3 and 255 characters")
        @Column(nullable = false)
        var email : String,

        var image : String?,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "University field field must be between 3 and 255 characters")
        @Column(nullable = false)
        var university : String,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "University Course field field must be between 3 and 255 characters")
        @Column(nullable = false)
        var universityCourse : String,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Registration Number field field must be between 3 and 255 characters")
        @Column(nullable = false)
        var registrationNumber : String,

        @OneToMany(mappedBy = "attendee", cascade = [(CascadeType.ALL)])
        var events : Set<AttendeeToEvent>,

        @CreatedDate
        var createdDate: Instant,

        @LastModifiedDate
        var modifiedDate: Instant,
)
