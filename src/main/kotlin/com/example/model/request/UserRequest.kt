package com.example.model.request

import io.micronaut.serde.annotation.Serdeable
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Serdeable
@Schema(description = "Payload used to create/update a user")
data class UserRequest(
    @field:NotBlank
    @field:Size(min = 2, max = 100)
    @Schema(example = "Jane Doe")
    val name: String,

    @field:NotBlank
    @field:Email
    @Schema(example = "jane.doe@example.com")
    val email: String,

    @field:Positive
    @Schema(example = "1")
    val countryId: Long
)