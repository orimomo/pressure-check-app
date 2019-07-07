package com.example.low_pressure_check.model

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("37.8267,-122.4233")
    suspend fun getForecast(
    ): Response<ForecastEntity>
}