package com.pm.patientservice.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//Centralise exception handling and keep service and controllers clean

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<Map<String, String>> {

        val errors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }

        return ResponseEntity.badRequest().body(errors)
    }

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailAlreadyExists(
        ex: EmailAlreadyExistsException
    ): ResponseEntity<Map<String, String>> {

        log.warn("Email address already exists: {}", ex.message)

        return ResponseEntity
            .badRequest()
            .body(mapOf("email" to (ex.message ?: "Email already exists")))
    }


    @ExceptionHandler(PatientNotFoundException::class)
    fun handlePatientNotFound(
        ex: PatientNotFoundException
    ): ResponseEntity<Map<String, String>> {

        log.warn("Patient not found: {}", ex.message)

        return ResponseEntity
            .status(404)
            .body(
                mapOf(
                    "error" to (ex.message ?: "Patient not found")
                )
            )
    }
}