package com.pm.patientservice.service

import com.pm.patientservice.dto.PatientRequestDTO
import com.pm.patientservice.dto.PatientResponseDTO
import com.pm.patientservice.exception.EmailAlreadyExistsException
import com.pm.patientservice.exception.PatientNotFoundException
import com.pm.patientservice.mapper.toDto
import com.pm.patientservice.mapper.toEntity
import com.pm.patientservice.repository.PatientRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

@Service
public class PatientService (
    private val repository: PatientRepository
) {

    fun getPatients(): List<PatientResponseDTO> {
        return repository.findAll().map { it.toDto() }
    }

    fun createNewPatient(patient: PatientRequestDTO): PatientResponseDTO {

        if (repository.existsByEmail(patient.email)) {
            throw EmailAlreadyExistsException()
        }
        val newPatient = repository.save(patient.toEntity())
        return newPatient.toDto()
    }

    fun updatePatient(id : UUID, request: PatientRequestDTO): PatientResponseDTO {
        val patient = repository.findById(id)
            .orElseThrow {
                PatientNotFoundException("Patient with id $id not found")
            }

        if (patient.email != request.email &&
            repository.existsByEmail(request.email)
        ) {
            throw EmailAlreadyExistsException()
        }

        patient.firstName = request.firstName
        patient.lastName = request.lastName
        patient.email = request.email
        patient.address = request.address
        patient.dateOfBirth = LocalDate.parse(request.dateOfBirth)

        val updatedPatient = repository.save(patient)

        return updatedPatient.toDto()
    }
}