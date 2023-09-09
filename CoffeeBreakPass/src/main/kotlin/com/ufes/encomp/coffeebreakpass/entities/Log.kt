package com.ufes.encomp.coffeebreakpass.entities

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.Instant

@Entity
@Table(name = "logs")
data class Log (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,

    var log : String,

    var category : String,

    @CreatedDate
    var createdDate: Instant,
)