package com.ufes.encomp.coffeebreakpass.exception

import com.ufes.encomp.coffeebreakpass.enums.defaultNotFoundMessage
import org.springframework.http.HttpStatus

class CustomEntityNotFoundException (
    private val entityName : String,

    override val statusCode: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message : String? = defaultNotFoundMessage(entityName)
) : CustomExceptions()