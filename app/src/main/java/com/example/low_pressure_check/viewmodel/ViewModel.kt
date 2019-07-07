package com.example.low_pressure_check.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.low_pressure_check.model.Repository

class ViewModel(private val repository: Repository): ViewModel() {

    val forecast = MutableLiveData<String>()

    suspend fun fetchForecast() {
        try {
            val res = repository.getForecast()
            if(res.isSuccessful) {
                res.body()?.timezone?.let { timezone ->
                    forecast.value = timezone
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