package com.example.low_pressure_check.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.low_pressure_check.model.Repository
import kotlinx.coroutines.launch

class ViewModel(private val repository: Repository): ViewModel(), LifecycleObserver {
    val pressure = MutableLiveData<String>()
    val status = MutableLiveData<Status>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Suppress("UNUSED")
    fun onCreate() = viewModelScope.launch {
        fetchForecast()
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

    enum class Status {
        LOADING,
        COMPLETED,
        FAILED
    }
}