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
    val place = MutableLiveData<String>()
    val status = MutableLiveData<Status>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Suppress("UNUSED")
    fun onCreate() = viewModelScope.launch {
        setDefaultInfo()
    }

    private suspend fun setDefaultInfo() {
        status.value = Status.LOADING
        try {
            val res = repository.getForecast()
            if(res.isSuccessful) {
                res.body()?.currently?.let { currently ->
                    pressure.value = currently.pressure
                }
                setToday()
                place.value = "@Tokyo"
                status.value = Status.COMPLETED
            } else {
                status.value = Status.FAILED
            }
        } catch(e: Exception) {
            status.value = Status.FAILED
        }
    }

    private fun setToday() {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        today.value = LocalDate.now().format(formatter)
    }

    fun changePlace(newPlace: String) {
        place.value = newPlace
    }

    enum class Status {
        LOADING,
        COMPLETED,
        FAILED
    }
}