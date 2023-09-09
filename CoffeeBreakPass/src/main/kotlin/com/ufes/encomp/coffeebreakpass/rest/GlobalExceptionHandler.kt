package com.ufes.encomp.coffeebreakpass.rest

import com.ufes.encomp.coffeebreakpass.exception.CustomDataIntegrityViolationException
import com.ufes.encomp.coffeebreakpass.exception.CustomExceptions
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrityViolation(exception: DataIntegrityViolationException) : ResponseEntity<String>{
        val treatedException = CustomDataIntegrityViolationException(
            exceptionMessage = exception.mostSpecificCause.message)

        return ResponseEntity.status(treatedException.statusCode)
            .body(treatedException.message)
    }

    @ExceptionHandler(CustomExceptions::class)
    fun customExceptionsHandler(exception : CustomExceptions) = ResponseEntity
        .status(exception.statusCode).body(exception.message)
}