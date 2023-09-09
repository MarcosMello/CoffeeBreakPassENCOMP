package com.ufes.encomp.coffeebreakpass.exception

import com.google.gson.Gson
import com.ufes.encomp.coffeebreakpass.enums.defaultDuplicateMessage
import com.ufes.encomp.coffeebreakpass.exception.entities.ValidationErrorNotUniqueEntity
import org.springframework.http.HttpStatus

class CustomDataIntegrityViolationException (
    exceptionMessage : String?,

    override val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override var message : String? = null
) : CustomExceptions(){
    init {
        message = if (exceptionMessage != null && exceptionMessage.contains("Duplicate entry")) {
            val violatedConstraint = exceptionMessage
                .split(" ").last().removeSurrounding("'")

            Gson().toJson(
                ValidationErrorNotUniqueEntity(
                    mapOf(
                        Pair(
                            violatedConstraint,
                            listOf(
                                defaultDuplicateMessage(violatedConstraint
                                    .replaceFirstChar {
                                        it.uppercaseChar()
                                    }
                                )
                            )
                        )
                    )
                )
            )
        } else exceptionMessage
    }
}
