package com.example.support.util

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import jakarta.validation.ConstraintViolationException

@Singleton
@Produces // default to application/json
class ValidationErrorHandler :
    ExceptionHandler<ConstraintViolationException, HttpResponse<Map<String, Any>>> {

    override fun handle(request: HttpRequest<*>, exception: ConstraintViolationException): HttpResponse<Map<String, Any>> {
        val errors = exception.constraintViolations.map { v ->
            mapOf(
                "property" to v.propertyPath.toString(),
                "message" to v.message
            )
        }
        val body = mapOf(
            "status" to 400,
            "error" to "Bad Request",
            "violations" to errors
        )
        return HttpResponse.badRequest(body)
    }
}