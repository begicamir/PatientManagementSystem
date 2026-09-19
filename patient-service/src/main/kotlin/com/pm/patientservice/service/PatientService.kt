package com.pm.patientservice.service

import com.pm.patientservice.dto.PatientResponseDTO
import com.pm.patientservice.mapper.toDto
import com.pm.patientservice.model.Patient
import com.pm.patientservice.repository.PatientRepository
import org.springframework.stereotype.Service

@Service
public class PatientService (
    private val repository: PatientRepository
) {

    public fun getPatients(): List<PatientResponseDTO> {
        return repository.findAll().map { it.toDto() }
    }
}