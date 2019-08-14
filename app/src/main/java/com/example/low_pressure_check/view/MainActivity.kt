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
        setContentView(R.layout.activity_main)

        // ViewModelにライフサイクルを連動させるために必要
        lifecycle.addObserver(viewModel)

        // LiveDataを更新するために必要
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.status.observe(this, Observer { status ->
            when (status) {
                ViewModel.Status.LOADING -> {
                    binding.progressBar.isVisible = true
                    binding.textView.isGone = true
                }
                ViewModel.Status.COMPLETED -> {
                    binding.progressBar.isGone = true
                    viewModel.pressure.value?.let { pressure ->
                        binding.textView.changeSizeOfText(pressure, 56)
                        binding.textView.isVisible = true
                    }
                }
                ViewModel.Status.FAILED -> {
                    Snackbar.make(binding.root , "データの取得に失敗しました。", Snackbar.LENGTH_LONG).show()
                }
                else -> Unit
            }
        })
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