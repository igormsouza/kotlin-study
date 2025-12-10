package com.example.controller

import com.example.model.base.BaseController
import com.example.model.domain.Country
import com.example.model.request.CountryRequest
import com.example.service.CountryService
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.tags.Tag

@Validated
@Tag(name = "Countries")
@Controller("/api/countries")
open class CountryController (service: CountryService) : BaseController<Country, CountryRequest, CountryService>(service)