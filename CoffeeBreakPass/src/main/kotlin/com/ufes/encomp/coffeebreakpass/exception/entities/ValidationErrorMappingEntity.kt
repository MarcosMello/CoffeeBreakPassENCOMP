package com.ufes.encomp.coffeebreakpass.exception.entities

data class ValidationErrorMappingEntity (
    var errors : MutableMap<String, MutableList<String?>> = mutableMapOf()
)