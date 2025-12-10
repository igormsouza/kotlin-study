package com.example.controller

import com.example.model.domain.User
import com.example.model.request.UserRequest
import com.example.model.base.BaseController
import com.example.service.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.tags.Tag

@Validated
@Tag(name = "Users")
@Controller("/api/users")
class UserController (service: UserService): BaseController<User, UserRequest, UserService>(service) {
}