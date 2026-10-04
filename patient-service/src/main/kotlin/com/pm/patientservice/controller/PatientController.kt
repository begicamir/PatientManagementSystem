package com.pm.patientservice.controller

import com.pm.patientservice.dto.PatientRequestDTO
import com.pm.patientservice.dto.PatientResponseDTO
import com.pm.patientservice.repository.PatientRepository
import com.pm.patientservice.service.PatientService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/patients")
class PatientController (
    private val patientService: PatientService,
) {
    @GetMapping
    fun getAllPatients(): ResponseEntity<List<PatientResponseDTO>> {
        return ResponseEntity(patientService.getPatients(),HttpStatus.OK )
    }

    @PostMapping
   fun createNewPatient(@Valid @RequestBody patient: PatientRequestDTO): ResponseEntity<PatientResponseDTO> {
       return ResponseEntity.ok().body(patientService.createNewPatient(patient))
   }
}