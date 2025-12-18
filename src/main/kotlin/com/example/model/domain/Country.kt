package com.example.model.domain

import com.example.model.base.BaseDomain
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Serdeable
@MappedEntity("countries")
data class Country (
    @field:Id
    override val id: Long = 0,

    @field:NotBlank(message = "name is required")
    @field:Size(min = 2, max = 100, message = "name must be between 2 and 100 chars")
    val name: String,
) : BaseDomain