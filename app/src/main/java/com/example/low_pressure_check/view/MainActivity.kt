package com.example.low_pressure_check.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.low_pressure_check.R
import com.example.low_pressure_check.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    val viewModel by viewModel<ViewModel>()

    override val coroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_main)

        launch {
            viewModel.fetchForecast()
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
