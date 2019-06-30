package com.example.low_pressure_check

import com.example.low_pressure_check.model.ApiClient
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object KoinModule {
    val modules = listOf(
        dataModule(),
        domainModule(),
        uiModule()
    )

    private fun dataModule() = module {
        single { ApiClient.retrofit }
//        single { ArticleRepository(get()) }
    }

    private fun domainModule() = module {
//        single { ArticleUseCase(get()) }
    }

    private fun uiModule() = module {
//        viewModel { ListViewModel(get()) }
    }

}