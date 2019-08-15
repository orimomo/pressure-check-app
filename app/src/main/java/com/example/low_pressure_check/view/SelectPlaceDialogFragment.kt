package com.example.low_pressure_check.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.low_pressure_check.databinding.DialogSelectPlaceBinding

class SelectPlaceDialogFragment : DialogFragment() {

    private lateinit var binding: DialogSelectPlaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        binding = DialogSelectPlaceBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }
}