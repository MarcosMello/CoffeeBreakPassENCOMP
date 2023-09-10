package com.ufes.encomp.coffeebreakpass.rest

import com.ufes.encomp.coffeebreakpass.entities.User
import com.ufes.encomp.coffeebreakpass.enums.defaultSuccessMessage
import com.ufes.encomp.coffeebreakpass.exception.CustomValidationException
import com.ufes.encomp.coffeebreakpass.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api")
class UserRestController(private val userService: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<List<User>> = ResponseEntity
        .status(HttpStatus.OK).body(userService.getAllUsers())

    @GetMapping("/user/{id}")
    fun getUserByID(@PathVariable("id") userID: Long): ResponseEntity<User> = ResponseEntity
        .status(HttpStatus.OK).body(userService.getUserByID(userID))

    @PostMapping("/user")
    fun createUser(@RequestBody @Valid userRequest: User, errors: BindingResult): ResponseEntity<String> {
        if (errors.hasErrors()) throw CustomValidationException(errors = errors)

        return ResponseEntity.status(HttpStatus.OK).body(
            defaultSuccessMessage(
                action = "created",
                entityName = "User",
                entityID = (userService.create(userRequest).id).toString()
            )
        )
    }

    @PutMapping("/user/{id}")
    fun updateUserByID(
        @PathVariable("id") userID: Long,
        @RequestBody @Valid userRequest: User,
        errors: BindingResult
    ): ResponseEntity<String> {
        if (errors.hasErrors()) throw CustomValidationException(errors = errors)

        return ResponseEntity.status(HttpStatus.OK).body(
            defaultSuccessMessage(
                action = "updated",
                entityName = "User",
                entityID = (userService.update(userID, userRequest).id).toString()
            )
        )
    }

    @DeleteMapping("/user/{id}")
    fun deleteUserByID(@PathVariable("id") userID: Long): ResponseEntity<String> {
        userService.delete(userID)

        return ResponseEntity.status(HttpStatus.OK).body(
            defaultSuccessMessage(
                action = "deleted",
                entityName = "User",
                entityID = (userID).toString()
            )
        )
    }
}