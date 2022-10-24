package com.rafaelleal.android.dialogsproject.dialogfragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.TimePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import java.sql.Time


////////////////////////////////////////////////////////////////////////////////////////////////////
// https://developer.android.com/guide/fragments/dialogs ///////////////////////////////////////////


// Certifique-se de importar: androidx.fragment.app.DialogFragment
class TimePickerFragment : DialogFragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Usar o Safe Args para transmitir dados com a segurança de tipo //////////////////////////////
    // https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args ///////////////
    // Recebe argumentos de navegação //////////////////////////////////////////////////////////////
    private val args: TimePickerFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val timeListener = TimePickerDialog.OnTimeSetListener {
                _: TimePicker, hora: Int, minuto: Int ->
            val resultTime = Time( hora, minuto, 0 )
            setFragmentResult(REQUEST_KEY_TIME,
                bundleOf(BUNDLE_KEY_TIME to resultTime)
            )

        }

        val calendar = Calendar.getInstance()

        // Recebe argumento passado pelo safeArgs
        calendar.time = args.yourTime

        val initialHour = calendar.get(Calendar.HOUR)
        val initialMinute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireContext(),
            timeListener,
            initialHour,
            initialMinute,
            true
        )
    }

    companion object{
        val REQUEST_KEY_TIME = "Chave de hora"
        val BUNDLE_KEY_TIME = "BUNDLE_KEY_TIME"
    }

}