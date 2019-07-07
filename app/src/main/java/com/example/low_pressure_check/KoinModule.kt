package com.example.low_pressure_check

import com.example.low_pressure_check.model.ApiClient
import com.example.low_pressure_check.model.Repository
import com.example.low_pressure_check.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object KoinModule {
    val modules = listOf(
        dataModule(),
        uiModule()
    )

    private fun dataModule() = module {
        single { ApiClient.retrofit }
        single { Repository(get()) }
    }

    private fun uiModule() = module {
        viewModel { ViewModel(get()) }
    }

}