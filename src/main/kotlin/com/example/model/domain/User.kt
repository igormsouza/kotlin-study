package com.example.model.domain

import com.example.model.base.BaseDomain
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Serdeable
@MappedEntity("users")
data class User(
    @field:Id
    override val id: Long = 0,

    @field:NotBlank(message = "name is required")
    @field:Size(min = 2, max = 100, message = "name must be between 2 and 100 chars")
    val name: String,

    @field:NotBlank(message = "email is required")
    @field:Email(message = "email must be valid")
    val email: String,

    @field:Positive(message = "country is required")
    val countryId: Long,

    @Transient // <- do NOT persist; use for read-time enrichment only
    var country: Country? = null
) : BaseDomain