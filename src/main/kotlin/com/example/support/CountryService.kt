package com.example.support

import com.example.controller.model.domain.Country
import com.example.controller.model.request.CountryRequest
import com.example.support.base.BaseService
import jakarta.inject.Singleton

@Singleton
 open class CountryService : BaseService<Country, CountryRequest>(insertPredicate = { id, req ->
    Country(
        id = id,
        name =  req.name
    )
}, updatePredicate = { existing, req ->
    existing.copy(
        name = req.name
    )
})