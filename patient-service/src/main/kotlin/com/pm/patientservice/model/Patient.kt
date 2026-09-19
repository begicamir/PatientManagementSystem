package com.pm.patientservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.util.UUID

@Entity
class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = null,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Email
    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var dateOfBirth: LocalDate,

    @Column(nullable = false)
    var registeredDate: LocalDate
)