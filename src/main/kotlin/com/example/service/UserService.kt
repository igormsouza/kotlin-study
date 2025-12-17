package com.example.service

import com.example.model.domain.User
import com.example.model.request.UserRequest
import com.example.repository.CountryRepository
import com.example.repository.UserRepository
import com.example.service.base.BaseService
import jakarta.inject.Singleton

@Singleton
class UserService (val repo: UserRepository, val countryRepository: CountryRepository)
    : BaseService<User, UserRequest>(insertPredicate = { id, req ->
    User (
        id = id,
        name = req.name,
        email = req.email.lowercase(),
        countryId = req.countryId
    )
}, updatePredicate = { existing, req ->
    existing.copy(
        name = req.name,
        email = req.email.lowercase(),
        countryId = req.countryId
    )
}, repo = repo
){
    override fun preCreate(req: UserRequest): Boolean = validateCountryExistence(req)
    override fun preUpdate(req: UserRequest): Boolean = validateCountryExistence(req)

    fun validateCountryExistence(req: UserRequest): Boolean {
        val countryExists = countryRepository.existsById(req.countryId)
        require(countryExists) { "countryId does not exist: ${req.countryId}" }
        return true
    }
}
