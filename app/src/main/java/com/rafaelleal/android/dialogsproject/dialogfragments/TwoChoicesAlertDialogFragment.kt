package com.rafaelleal.android.dialogsproject.dialogfragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rafaelleal.android.dialogsproject.R


// Alert dialog
// https://m2.material.io/components/dialogs/android#alert-dialog

class TwoChoicesAlertDialogFragment(val listener: TwoChoicesAlertDialogFragmentListener) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let { fragmentActivity ->
            val builder = context?.let { itContext ->
                MaterialAlertDialogBuilder(itContext)
                    .setTitle(resources.getString(R.string.title_two_choices_alert_dialog))
                    .setMessage(resources.getString(R.string.supporting_text_two_choices_alert_dialog))
                    .setNegativeButton(resources.getString(R.string.decline_two_choices_alert_dialog)) { dialog, which ->
                        // Respond to negative button press
                        listener.onNegativeButtonClick()
                    }
                    .setPositiveButton(resources.getString(R.string.accept_two_choices_alert_dialog)) { dialog, which ->
                        // Respond to positive button press
                        listener.onPositiveButtonClick()
                    }
            }
            builder?.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}

interface TwoChoicesAlertDialogFragmentListener {
    fun onPositiveButtonClick()
    fun onNegativeButtonClick()
}