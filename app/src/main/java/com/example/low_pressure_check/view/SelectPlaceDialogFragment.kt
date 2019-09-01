package com.example.low_pressure_check.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.low_pressure_check.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectPlaceDialogFragment : DialogFragment() {

    private val viewModel by sharedViewModel<ViewModel>()
    private var checkedItem = -1

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel.place.value?.let { place ->
            checkedItem = placeList.indexOf(place)
        }

        return AlertDialog.Builder(activity)
            .setTitle("Choose a place!")
            .setSingleChoiceItems(placeList, checkedItem) { _, which ->
                checkedItem = which
            }
            .setPositiveButton("OK") { _, _ ->
                viewModel.changeInfo(placeList[checkedItem])
            }
            .setNegativeButton("CANCEL", null)
            .create()
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    companion object {
        private val placeList = arrayOf(
            "Hokkaido",
            "Tokyo",
            "Osaka",
            "Okinawa"
        )
    }
}