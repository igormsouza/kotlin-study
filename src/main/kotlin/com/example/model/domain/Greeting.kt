package com.example.model.domain

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Greeting(val message: String)