package com.example.low_pressure_check.model

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("35.658851, 139.745455") // 東京タワーの緯度経度
    suspend fun getForecast(
    ): Response<CurrentlyEntity>
}