package com.example.controller

import com.example.controller.model.base.BaseController
import com.example.controller.model.domain.Country
import com.example.controller.model.request.CountryRequest
import com.example.support.CountryService
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.tags.Tag

@Validated
@Tag(name = "Users")
@Controller("/api/countries")
open class CountryController (service: CountryService) : BaseController<Country, CountryRequest, CountryService>(service)