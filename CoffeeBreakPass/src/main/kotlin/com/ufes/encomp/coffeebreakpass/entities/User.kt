package com.ufes.encomp.coffeebreakpass.entities

import com.ufes.encomp.coffeebreakpass.enums.RoleEnum
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@Entity
@Table(name = "users", uniqueConstraints = [
        UniqueConstraint(name = "email", columnNames = ["email"])
])
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long,

        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Username field must be between 3 and 255 characters")
        @Column(nullable = false)
        var name : String,

        @field:Email(message = "Email field must be a well-formed email address")
        @field:NotNull
        @field:NotBlank
        @field:Size(min = 3, max = 255, message = "Email field must be between 3 and 255 characters")
        @Column(nullable = false)
        var email : String,

        @field:Size(min = 8, max = 255, message = "Password field must be between 8 and 255 characters")
        @Column(nullable = false)
        private var password : String,

        @field:NotNull
        @Enumerated(EnumType.STRING)
        @Column(nullable = false, columnDefinition = "ENUM")
        var role : RoleEnum,

        @field:NotNull
        @Column(nullable = false)
        var enabled : Boolean = false,

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var createdDate: Date = Date(),

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var modifiedDate: Date = Date(),
) {
        @PreUpdate
        private fun onUpdate(){
                this.modifiedDate = Date()
        }

        fun updatePassword(actualPassword : String, newPassword : String) {
                if (actualPassword == BCryptPasswordEncoder()
                        .encode(newPassword)) {
                        this.password = newPassword
                }
        }
}