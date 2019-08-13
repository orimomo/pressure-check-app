package com.example.low_pressure_check.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.low_pressure_check.model.Repository
import kotlinx.coroutines.launch

class ViewModel(private val repository: Repository): ViewModel(), LifecycleObserver {
    val summary = MutableLiveData<String>()
    val icon = MutableLiveData<String>()
    val pressure = MutableLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Suppress("UNUSED")
    fun onCreate() = viewModelScope.launch {
        fetchForecast()
    }

    private suspend fun fetchForecast() {
        try {
            val res = repository.getForecast()
            if(res.isSuccessful) {
                res.body()?.currently?.let { currently ->
                    summary.value = currently.summary
                    icon.value = currently.icon
                    pressure.value = currently.pressure
                }
            } else {
                //Timberを入れたい
                // https://qiita.com/hkusu/items/d4f24141d11e05f57451
            }
        } catch(e: Exception) {
            //Timberを入れたい
            //ちゃんとエラーハンドリングしたい
        }
    }
}