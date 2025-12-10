package com.example.repository

import com.example.model.domain.Country
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface CountryRepository : CrudRepository<Country, Int>