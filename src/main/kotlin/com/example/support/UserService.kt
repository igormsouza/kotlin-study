package com.example.support

import com.example.controller.model.domain.User
import com.example.controller.model.request.UserRequest
import com.example.support.base.BaseService
import jakarta.inject.Singleton

@Singleton
class UserService : BaseService<User, UserRequest>(insertPredicate = { id, req ->
    User (
        id = id,
        name = req.name,
        email = req.email.lowercase()
    )
}, updatePredicate = { existing, req ->
    existing.copy(
        name = req.name,
        email = req.email.lowercase()
    )
})
