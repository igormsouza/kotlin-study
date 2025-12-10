package com.example.service

import com.example.model.domain.User
import com.example.model.request.UserRequest
import com.example.service.base.BaseService
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
