package com.example.model.domain

import com.example.model.base.BaseDomain
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable.Deserializable
import io.micronaut.serde.annotation.Serdeable.Serializable
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Serializable
@Deserializable
@Schema(description = "User")
@MappedEntity("users")
data class User(
    @field:Id
    @Schema(description = "Unique identifier", example = "1")
    override val id: Long = 0,

    @field:NotBlank(message = "name is required")
    @field:Size(min = 2, max = 100, message = "name must be between 2 and 100 chars")
    @Schema(description = "User full name", example = "Jane Doe")
    val name: String,

    @field:NotBlank(message = "email is required")
    @field:Email(message = "email must be valid")
    @Schema(description = "Email address", example = "jane.doe@example.com")
    val email: String
) : BaseDomain