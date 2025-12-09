//
//package com.example.controller
//
//import com.example.controller.model.domain.User
//import com.example.controller.model.request.UserRequest
//import com.example.support.UserService
//import io.micronaut.http.HttpHeaders
//import io.micronaut.http.HttpResponse
//import io.micronaut.http.MediaType
//import io.micronaut.http.annotation.*
//import io.micronaut.validation.Validated
//import io.swagger.v3.oas.annotations.tags.Tag
//import jakarta.validation.Valid
//import jakarta.validation.constraints.Min
//
//
//@Validated
//@Tag(name = "Users")
//@Controller("/api/users")
//open class UserController(private val service: UserService) {
//
//    @Get(produces = [MediaType.APPLICATION_JSON])
//    open fun list(): List<User> = service.list()
//
//    @Get("/{id}")
//    open fun get(@PathVariable @Min(1) id: Long): HttpResponse<User> =
//        service.get(id)?.let { HttpResponse.ok(it) } ?: HttpResponse.notFound()
//
//    @Post
//    open fun create(@Body @Valid req: UserRequest): HttpResponse<User> {
//        val created = service.create(req)
//        return HttpResponse.created(created)
//            .header(HttpHeaders.LOCATION, "/api/users/${created.id}")
//    }
//
//    @Put("/{id}")
//    open fun update(
//        @PathVariable @Min(1) id: Long,
//        @Body @Valid req: UserRequest
//    ): HttpResponse<User> =
//        service.update(id, req)?.let { HttpResponse.ok(it) } ?: HttpResponse.notFound()
//
//    @Delete("/{id}")
//    open fun delete(@PathVariable @Min(1) id: Long): HttpResponse<Unit> =
//        if (service.delete(id))
//            HttpResponse.noContent()
//        else
//            HttpResponse.notFound()
//}
