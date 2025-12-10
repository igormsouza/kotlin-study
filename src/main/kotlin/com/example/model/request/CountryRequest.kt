package com.example.model.request

import io.micronaut.serde.annotation.Serdeable
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Serdeable
@Schema(description = "Payload used to create/update a country")
data class CountryRequest (
    @field:NotBlank
    @field:Size(min = 2, max = 100)
    @Schema(example = "Spain")
    val name: String,) {
}