package com.example.low_pressure_check.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.low_pressure_check.model.Repository
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class ViewModel(private val repository: Repository): ViewModel(), LifecycleObserver {
    val pressure = MutableLiveData<String>()
    val today = MutableLiveData<String>()
    val place = MutableLiveData<String>("@Tokyo")
    val status = MutableLiveData<Status>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Suppress("UNUSED")
    fun onCreate() = viewModelScope.launch {
        fetchForecast(Place.TOKYO.coordinates)
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        today.value = LocalDate.now().format(formatter)
    }

    private suspend fun fetchForecast(coordinates: String) {
        status.value = Status.LOADING
        try {
            val res = repository.getForecast(coordinates)
            if(res.isSuccessful) {
                res.body()?.currently?.let { currently ->
                    pressure.value = currently.pressure
                }
                status.value = Status.COMPLETED
            } else {
                status.value = Status.FAILED
            }
        } catch(e: Exception) {
            status.value = Status.FAILED
        }
    }

    fun changeInfo(newPlace: Place) = viewModelScope.launch {
        when(newPlace) {
            Place.HOKKAIDO -> {
                place.value = Place.HOKKAIDO.label
                fetchForecast(Place.HOKKAIDO.coordinates)
            }
            Place.TOKYO -> {
                place.value = Place.TOKYO.label
                fetchForecast(Place.TOKYO.coordinates)
            }
            Place.OSAKA -> {
                place.value = Place.OSAKA.label
                fetchForecast(Place.OSAKA.coordinates)
            }
            Place.OKINAWA -> {
                place.value = Place.OKINAWA.label
                fetchForecast(Place.OKINAWA.coordinates)
            }
        }
    }

    enum class Status {
        LOADING,
        COMPLETED,
        FAILED
    }

    enum class Place(val label: String, val coordinates: String) {
        HOKKAIDO("@Hokkaido", "43.069776, 141.350139"), // さっぽろ駅の座標
        TOKYO("@Tokyo", "35.681541, 139.767114"), // 東京駅の座標
        OSAKA("@Osaka", "34.702768, 135.495951"), // 大阪駅の座標
        OKINAWA("@Okinawa", "26.206692, 127.646531") // 那覇空港の座標
    }
}