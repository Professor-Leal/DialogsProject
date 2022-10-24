package com.rafaelleal.android.dialogsproject.dialogfragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs

////////////////////////////////////////////////////////////////////////////////////////////////////
// https://developer.android.com/guide/fragments/dialogs ///////////////////////////////////////////


// Certifique-se de importar: androidx.fragment.app.DialogFragment
class DatePickerFragment : DialogFragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Usar o Safe Args para transmitir dados com a segurança de tipo //////////////////////////////
    // https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args ///////////////
    // Recebe argumentos de navegação //////////////////////////////////////////////////////////////
    private val args: DatePickerFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dateListener = DatePickerDialog.OnDateSetListener {
                _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate = GregorianCalendar(year, month, day).time
            setFragmentResult(REQUEST_KEY_DATE,
                bundleOf(BUNDLE_KEY_DATE to resultDate))
        }



        val calendar = Calendar.getInstance()

        // Recebe argumento passado pelo safeArgs
        calendar.time = args.yourDate

        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object{
        val REQUEST_KEY_DATE = "Chave de data"
        val BUNDLE_KEY_DATE = "BUNDLE_KEY_DATE"
    }

}