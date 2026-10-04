package com.pm.patientservice.mapper

import com.pm.patientservice.dto.PatientRequestDTO
import com.pm.patientservice.dto.PatientResponseDTO
import com.pm.patientservice.model.Patient
import java.time.LocalDate


fun Patient.toDto(): PatientResponseDTO {
        return PatientResponseDTO(
            id = this.id.toString(),
            name = this.firstName + " " + this.lastName,
            address = this.address,
            email = this.email,
            dateOfBirth = this.dateOfBirth.toString()
        )
}

fun PatientRequestDTO.toEntity(): Patient {
    return Patient(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        address = this.address,
        dateOfBirth = LocalDate.parse(this.dateOfBirth),
        registeredDate = LocalDate.parse(this.registeredDate)
    )
}