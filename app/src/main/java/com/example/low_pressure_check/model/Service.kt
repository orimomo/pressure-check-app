package com.example.low_pressure_check.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("{coordinates}")
    suspend fun getForecast(
        @Path("coordinates") coordinates: String = ""
    ): Response<CurrentlyEntity>
}