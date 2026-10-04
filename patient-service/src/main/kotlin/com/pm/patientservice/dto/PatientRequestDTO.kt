package com.pm.patientservice.dto

import com.pm.patientservice.dto.validators.CreatePatientValidationGroup
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PatientRequestDTO(
    @field:NotBlank(message = "First name is required.")
    @field:Size(max = 100, message = "Name cannot exceed 100 characters")
    val firstName: String,

    @field:NotBlank(message = "Last name is required.")
    @field:Size(max = 100, message = "Name cannot exceed 100 characters")
    val lastName: String,

    @field:NotBlank(message = "Email is required.")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Address is required.")
    val address: String,

    @field:NotBlank(message = "Date of Birth is required.")
    val dateOfBirth: String,

    @field:NotBlank(groups = [CreatePatientValidationGroup::class],message = "Registred date is required.")
    val registeredDate: String? = null,


)