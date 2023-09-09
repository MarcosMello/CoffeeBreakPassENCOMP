package com.ufes.encomp.coffeebreakpass.exception

import com.google.gson.Gson
import com.ufes.encomp.coffeebreakpass.exception.entities.ValidationErrorNotUniqueEntity
import org.springframework.http.HttpStatus

class CustomUniqueConstraintsException (
    private val notUniqueMap : Map<String, List<String>>,

    override val statusCode: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message : String? = Gson().toJson(
        ValidationErrorNotUniqueEntity(notUniqueMap)
    ),
) : CustomExceptions()