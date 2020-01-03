package com.example.low_pressure_check.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.low_pressure_check.R
import com.example.low_pressure_check.databinding.ActivityMainBinding
import com.example.low_pressure_check.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val viewModel by viewModel<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModelにライフサイクルを連動させるために必要
        lifecycle.addObserver(viewModel)

        // LiveDataを更新するために必要
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.button.setOnClickListener {
            supportFragmentManager?.let { manager ->
                SelectPlaceDialogFragment().show(manager, SelectPlaceDialogFragment::class.simpleName)
            }
        }

        viewModel.status.observe(this, Observer { status ->
            when (status) {
                ViewModel.Status.LOADING -> {
                    binding.progressBar.isVisible = true
                    binding.textDate.isGone = true
                    binding.textPlace.isGone = true
                    binding.textPressure.isGone = true
                }
                ViewModel.Status.COMPLETED -> {
                    binding.progressBar.isGone = true
                    binding.textDate.isVisible = true
                    binding.textPlace.isVisible = true
                    viewModel.place.value?.let { place ->
                        setBackground(place)
                    }
                    viewModel.pressure.value?.let { pressure ->
                        binding.textPressure.changeSizeOfText(pressure, 56)
                        binding.textPressure.isVisible = true
                    }
                }
                ViewModel.Status.FAILED -> {
                    Snackbar.make(binding.root , "データの取得に失敗しました。", Snackbar.LENGTH_LONG).show()
                }
                else -> Unit
            }
        })
    }

    private fun setBackground(place: String) {
        when(place) {
            ViewModel.Place.HOKKAIDO.label -> binding.imageView.setImageResource(R.drawable.hokkaido)
            ViewModel.Place.TOKYO.label -> binding.imageView.setImageResource(R.drawable.tokyo)
            ViewModel.Place.OSAKA.label -> binding.imageView.setImageResource(R.drawable.osaka)
            ViewModel.Place.OKINAWA.label -> binding.imageView.setImageResource(R.drawable.okinawa)
        }
    }
}

fun TextView.changeSizeOfText(target: String, size: Int){
    val spannable = SpannableStringBuilder(target + "hPa")
    spannable.setSpan(
        AbsoluteSizeSpan(size, true),
        0,
        target.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannable
}