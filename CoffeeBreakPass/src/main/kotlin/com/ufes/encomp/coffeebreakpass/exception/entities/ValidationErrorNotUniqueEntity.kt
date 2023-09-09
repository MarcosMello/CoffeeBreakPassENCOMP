package com.ufes.encomp.coffeebreakpass.exception.entities

data class ValidationErrorNotUniqueEntity (
    val errors : Map<String, List<String>>
)