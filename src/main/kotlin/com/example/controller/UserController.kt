package com.example.controller

import com.example.controller.model.domain.User
import com.example.controller.model.request.UserRequest
import com.example.controller.model.base.BaseController
import com.example.support.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.tags.Tag

@Validated
@Tag(name = "Users")
@Controller("/api/users")
class UserController (service: UserService): BaseController<User, UserRequest, UserService>(service) {
}