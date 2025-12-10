package com.example.repository

import com.example.model.domain.User
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface UserRepository : CrudRepository<User, Int>