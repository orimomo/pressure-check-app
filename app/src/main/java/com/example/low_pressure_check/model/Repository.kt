package com.example.low_pressure_check.model

import retrofit2.Response
import retrofit2.Retrofit

class Repository(private val retrofit: Retrofit) {
    private val service by lazy { retrofit.create(Service::class.java) }

    suspend fun getForecast(coordinates: String): Response<CurrentlyEntity> {
        return service.getForecast(coordinates)
    }
}