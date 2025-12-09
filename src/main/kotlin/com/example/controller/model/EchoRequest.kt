package com.example.controller.model

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Serdeable
data class EchoRequest(
    // In Kotlin, use @field:… so the validator targets the backing field
    @field:NotBlank
    val text: String
)
