package com.pm.patientservice.repository

import com.pm.patientservice.model.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository : JpaRepository<Patient, Long> {
    
}