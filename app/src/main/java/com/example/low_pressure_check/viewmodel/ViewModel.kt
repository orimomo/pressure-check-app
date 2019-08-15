package com.example.low_pressure_check.viewmodel

import androidx.annotation.DrawableRes
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
        fetchForecast()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        today.value = LocalDate.now().format(formatter)
    }

    private suspend fun fetchForecast() {
        status.value = Status.LOADING
        try {
            val res = repository.getForecast()
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
                place.value = "@Hokkaido"
            }
            Place.TOKYO -> {
                place.value = "@Tokyo"
            }
            Place.OSAKA -> {
                place.value = "@Osaka"
            }
            Place.HAKATA -> {
                place.value = "@Hakata"
            }
        }
    }

    enum class Status {
        LOADING,
        COMPLETED,
        FAILED
    }

    enum class Place {
        HOKKAIDO,
        TOKYO,
        OSAKA,
        HAKATA
    }
}