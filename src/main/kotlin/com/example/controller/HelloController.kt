package com.example.controller

import com.example.model.request.EchoRequest
import com.example.model.domain.Greeting
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

@Validated
@Tag(name = "Home")
@Controller("/api")
open class HelloController {
    @Get(uri = "/hello", produces = [MediaType.TEXT_PLAIN])
    fun hello(): String =
        "Hello from Micronaut + Kotlin!"

    @Get("/hello-json")
    fun helloJson(): Greeting =
        Greeting("Hello JSON!")

    @Get("/hello-json2")
    open fun helloJson2(@QueryValue @NotBlank name: String): Greeting =
        Greeting("Hello, $name!")

    @Post("/echo")
    open fun echo(@Body @Valid req: EchoRequest): Greeting =
        Greeting("Echo: ${req.text}")



}