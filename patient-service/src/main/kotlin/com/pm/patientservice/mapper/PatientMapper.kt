package com.pm.patientservice.mapper

import com.pm.patientservice.dto.PatientResponseDTO
import com.pm.patientservice.model.Patient



fun Patient.toDto(): PatientResponseDTO {
        return PatientResponseDTO(
            id = this.id.toString(),
            name = this.firstName + " " + this.lastName,
            address = this.address,
            email = this.email,
            dateOfBirth = this.dateOfBirth.toString()
        )
}
