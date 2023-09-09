package com.ufes.encomp.coffeebreakpass.exception

import com.google.gson.Gson
import com.ufes.encomp.coffeebreakpass.exception.entities.ValidationErrorMappingEntity
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

class CustomValidationException(
    override val statusCode: HttpStatus = HttpStatus.BAD_REQUEST,
    override var message : String? = null,

    errors : BindingResult
) : CustomExceptions(){
    init {
        val errorsEntity = ValidationErrorMappingEntity()

        errors.fieldErrors.forEach {errorsIterator ->
            errorsEntity.errors[errorsIterator.field]?.also{
                it.add(errorsIterator.defaultMessage)
            } ?: run{
                errorsEntity.errors[errorsIterator.field] =
                    mutableListOf(errorsIterator.defaultMessage)
            }
        }

        message = Gson().toJson(errorsEntity)
    }
}