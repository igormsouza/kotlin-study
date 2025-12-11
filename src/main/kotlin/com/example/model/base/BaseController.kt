package com.example.model.base

import com.example.service.base.BaseService
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import jakarta.validation.Valid
import jakarta.validation.constraints.Min

abstract class BaseController<Domain: BaseDomain, Req: Any, T: BaseService<Domain, Req>> (
    val service: T
) {
    @Get(produces = [MediaType.APPLICATION_JSON])
    open fun list(): List<Domain> = service.list()

    @Get("/{id}")
    open fun get(@PathVariable @Min(1) id: Long): HttpResponse<Domain> =
        service.get(id)?.let { HttpResponse.ok(it) } ?: HttpResponse.notFound()

    @Post
    open fun create(@Body @Valid req: Req): HttpResponse<Domain> {
        val created = service.create(req)
        return HttpResponse.created(created)
            .header(HttpHeaders.LOCATION, "/api/users/${created.id}")
    }

    @Put("/{id}")
    open fun update(
        @PathVariable @Min(1) id: Long,
        @Body @Valid req: Req
    ): HttpResponse<Boolean> =
        service.update(id, req)?.let { HttpResponse.ok(it) } ?: HttpResponse.notFound()

    @Delete("/{id}")
    open fun delete(@PathVariable @Min(1) id: Long): HttpResponse<Unit> =
        if (service.delete(id))
            HttpResponse.noContent()
        else
            HttpResponse.notFound()
}