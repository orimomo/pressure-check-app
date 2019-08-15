package com.example.low_pressure_check.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.low_pressure_check.R
import com.example.low_pressure_check.databinding.DialogSelectPlaceBinding
import com.example.low_pressure_check.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectPlaceDialogFragment : DialogFragment() {

    private val viewModel by sharedViewModel<ViewModel>()
    private lateinit var binding: DialogSelectPlaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        binding = DialogSelectPlaceBinding.inflate(inflater, container, false)

        binding.placeGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                binding.button.isEnabled = true
            }
        }

        binding.button.setOnClickListener {
            changePlace()
            dismiss()
        }

        return binding.root
    }

    private fun changePlace() {
        val checkedId = binding.placeGroup.checkedRadioButtonId
        if (checkedId == -1) return
        when(checkedId) {
            R.id.radio_button1 -> {
                viewModel.changeInfo(ViewModel.Place.HOKKAIDO)
            }
            R.id.radio_button2 -> {
                viewModel.changeInfo(ViewModel.Place.TOKYO)
            }
            R.id.radio_button3 -> {
                viewModel.changeInfo(ViewModel.Place.OSAKA)
            }
            R.id.radio_button4 -> {
                viewModel.changeInfo(ViewModel.Place.HAKATA)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}