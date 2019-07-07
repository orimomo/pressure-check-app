package com.example.low_pressure_check.model

import com.squareup.moshi.Json
import java.io.Serializable

data class ForecastEntity(
    @Json(name = "timezone")
    val timezone: String
) : Serializable