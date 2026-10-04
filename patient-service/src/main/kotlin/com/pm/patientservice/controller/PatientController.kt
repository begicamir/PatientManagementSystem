package com.pm.patientservice.controller

import com.pm.patientservice.dto.PatientRequestDTO
import com.pm.patientservice.dto.PatientResponseDTO
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup
import com.pm.patientservice.service.PatientService
import jakarta.validation.Valid
import jakarta.validation.groups.Default
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import kotlin.io.encoding.Base64

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
   fun createNewPatient(@Validated(
        Default::class,
        CreatePatientValidationGroup::class
    ) @RequestBody patient: PatientRequestDTO): ResponseEntity<PatientResponseDTO> {
       return ResponseEntity.ok().body(patientService.createNewPatient(patient))
   }

    @PutMapping("/{id}")
    fun updatePatient(
        @PathVariable id: UUID,
        @Valid
        @RequestBody request: PatientRequestDTO
    ): ResponseEntity<PatientResponseDTO> {

        val result = patientService.updatePatient(id, request)

        return ResponseEntity.ok(result)
    }
}