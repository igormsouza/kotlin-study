package com.example.service

import com.example.model.domain.Country
import com.example.model.request.CountryRequest
import com.example.repository.CountryRepository
import com.example.service.base.BaseService
import jakarta.inject.Singleton

@Singleton
open class CountryService (repo: CountryRepository) : BaseService<Country, CountryRequest>(insertPredicate = { id, req ->
    Country(
        id = id,
        name =  req.name
    )
}, updatePredicate = { existing, req ->
    existing.copy(
        name = req.name
    )
}, repo = repo
)