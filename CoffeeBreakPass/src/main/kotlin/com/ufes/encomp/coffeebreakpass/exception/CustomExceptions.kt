package com.ufes.encomp.coffeebreakpass.exception

import org.springframework.http.HttpStatus

abstract class CustomExceptions : Exception(){
    abstract val statusCode : HttpStatus
    override val message : String? = null
}