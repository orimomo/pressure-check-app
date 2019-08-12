package com.example.low_pressure_check.model

import com.squareup.moshi.Json
import java.io.Serializable

data class CurrentlyEntity(
    val currently: ContentEntity
) : Serializable

data class ContentEntity(
    val time: String
) : Serializable