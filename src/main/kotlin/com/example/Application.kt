package com.example

import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.Micronaut.run

// 👇 Add these two imports
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

// 👇 Add this annotation (you can change title/version later)
@OpenAPIDefinition(
    info = Info(
        title = "My API",
        version = "1.0",
        description = "Simple study project API built with Micronaut + Kotlin",
        contact = Contact(name = "Igor Moreira", email = "igorafff@gmail.com")
    )
)
object ApiDoc

fun main(args: Array<String>) {
    Micronaut.build()
        .args(*args)
        .packages("com.example") // ensures beans in this package are scanned
        .start()
}
