package com.ufes.encomp.coffeebreakpass.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.Date

@Entity
@Table(name = "users", uniqueConstraints = [
        UniqueConstraint(name = "username", columnNames = ["username"]),
        UniqueConstraint(name = "email", columnNames = ["email"])
])
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Username field must be between 3 and 255 characters")
        @Column(nullable = false)
        var username : String,

        @field:Email(message = "Email field must be a well-formed email address")
        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Email field must be between 3 and 255 characters")
        @Column(nullable = false)
        var email : String,

        @field:Size(min = 8, max = 255, message = "Password field must be between 8 and 255 characters")
        @Column(nullable = false)
        var password : String?,

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var createdDate: Date = Date(),

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var modifiedDate: Date = Date(),
){
        @PreUpdate
        private fun onUpdate(){
                this.modifiedDate = Date()
        }
}