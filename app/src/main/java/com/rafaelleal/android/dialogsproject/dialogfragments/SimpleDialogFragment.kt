package com.rafaelleal.android.dialogsproject.dialogfragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rafaelleal.android.dialogsproject.R

// Simple dialog
// https://m2.material.io/components/dialogs/android#simple-dialog

class SimpleDialogFragment(val listener: SimpleDialogFragmentListener) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let { fragmentActivity ->

            val cores = arrayOf(
                "Vermelho",
                "Verde",
                "Azul",
                "Branco"
            )

            val builder = context?.let { itContext ->
                MaterialAlertDialogBuilder(itContext)
                    .setTitle(resources.getString(R.string.title_simple_dialog))
                    .setItems(cores){ dialog, which ->
                        val resposta =
                        when( cores.get(which) ) {
                            "Vermelho" -> "#FFFF0000"
                            "Verde" -> "#FF00FF00"
                            "Azul" -> "#FF0000FF"
                            "Branco" -> "#FFFFFFFF"
                            else -> "#FFFFFFFF"
                        }
                        listener.onItemClick(resposta)
                    }
            }
            builder?.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}

interface SimpleDialogFragmentListener {
    fun onItemClick(cor: String)
}
