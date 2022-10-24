package com.rafaelleal.android.dialogsproject.fragments

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.rafaelleal.android.dialogsproject.databinding.FragmentHomeBinding
import com.rafaelleal.android.dialogsproject.dialogfragments.*
import java.sql.Time
import java.util.*

////////////////////////////////////////////////////////////////////////////////////////////////////
// https://developer.android.com/topic/libraries/view-binding#fragments ////////////////////////////

class HomeFragment : Fragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // https://developer.android.com/topic/libraries/view-binding#fragments ////////////////////////
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setup()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    fun setup() {
        setupClickListeners()
    }

    fun setupClickListeners() {
        binding.apply {
            btnPickDate.setOnClickListener {
                selectDate()
            }

            btnPickTime.setOnClickListener {
                selectTime()
            }

            btnTwoChoicesAlert.setOnClickListener {
                onTwoChoicesAlertDialogClick()
            }

            btnThreeChoicesAlert.setOnClickListener {
                onThreeChoicesAlertDialogClick()
            }

            btnSimpleDialog.setOnClickListener {
                onSimpleDialogClick()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveDate()
        receiveTime()
    }

    var yourDate = Date()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Transmitir dados entre destinos /////////////////////////////////////////////////////////////
    // https://developer.android.com/guide/navigation/navigation-pass-data /////////////////////////
    fun selectDate() {
        //        //Apenas navega
        //        findNavController().navigate(
        //            R.id.select_date
        //        )

        // HomeFragmentDirections.selectDate(yourDate) gerado pelo safeAgrgs Plugin
        // Envia a data selecionada em yourDate
        findNavController().navigate(
            HomeFragmentDirections.selectDate(yourDate)
        )
    }

    // Recebe a data escolhida em DatePickerFragment
    fun receiveDate() {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        setFragmentResultListener(
            DatePickerFragment.REQUEST_KEY_DATE
        ) { requestKey, bundle ->
            val newDate =
                bundle.getSerializable(DatePickerFragment.BUNDLE_KEY_DATE) as Date
            val dateString = simpleDateFormat.format(newDate)
            binding.tvPickedDate.text = "Data Selecionada: $dateString"
        }
    }

    var yourTime = Time(yourDate.time)

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Transmitir dados entre destinos /////////////////////////////////////////////////////////////
    // https://developer.android.com/guide/navigation/navigation-pass-data /////////////////////////
    fun selectTime() {
        //        //Apenas navega
        //        findNavController().navigate(
        //            R.id.selectTime
        //        )

        // HomeFragmentDirections.selectTime(yourTime) gerado pelo safeAgrgs Plugin
        // Envia a hora selecionada em yourTime
        findNavController().navigate(
            HomeFragmentDirections.selectTime(yourTime)
        )
    }

    // Recebe a data escolhida em DatePickerFragment
    fun receiveTime() {
        val simpleTimeFormat = SimpleDateFormat("HH:mm")
        setFragmentResultListener(
            TimePickerFragment.REQUEST_KEY_TIME
        ) { requestKey, bundle ->
            val newTime =
                bundle.getSerializable(TimePickerFragment.BUNDLE_KEY_TIME) as Time
            val timeString = simpleTimeFormat.format(newTime)
            binding.tvPickedTime.text = "Hora Selecionada: $timeString"
        }
    }

    // Constrói e implementa os eventos de cliques de um diálogo com dois botões
    fun onTwoChoicesAlertDialogClick() {
        val dialog = TwoChoicesAlertDialogFragment(
            object : TwoChoicesAlertDialogFragmentListener {
                override fun onPositiveButtonClick() {
                    Toast.makeText(requireContext(), "Aceitou!!!", Toast.LENGTH_SHORT).show()
                    binding.tvTwoChoicesAlert.text = "Escolha Selecionada: Aceitou."
                }

                override fun onNegativeButtonClick() {
                    Toast.makeText(requireContext(), "Rejeitou!!!", Toast.LENGTH_SHORT).show()
                    binding.tvTwoChoicesAlert.text = "Escolha Selecionada: Rejeitou."
                }
            }
        )
        dialog.show(childFragmentManager, "TwoChoicesAlert")
    }

    // Constrói e implementa os eventos de cliques de um diálogo com três botões
    fun onThreeChoicesAlertDialogClick() {
        val dialog = ThreeChoicesAlertDialogFragment(
            object : ThreeChoicesAlertDialogFragmentListener {
                override fun onPositiveButtonClick() {
                    Toast.makeText(requireContext(), "Quero!!!", Toast.LENGTH_SHORT).show()
                    binding.tvThreeChoicesAlert.text = "Escolha Selecionada: Quero."
                }

                override fun onNegativeButtonClick() {
                    Toast.makeText(requireContext(), "Não quero!!!", Toast.LENGTH_SHORT).show()
                    binding.tvThreeChoicesAlert.text = "Escolha Selecionada: Não quero."
                }

                override fun onNeutralButtonClick() {
                    Toast.makeText(requireContext(), "Só olhando!!!", Toast.LENGTH_SHORT).show()
                    binding.tvThreeChoicesAlert.text = "Escolha Selecionada: Só olhando."
                }
            }
        )
        dialog.show(childFragmentManager, "ThreeChoicesAlert")
    }

    // Constrói e implementa os eventos de cliques de um diálogo simples com diversas opções
    fun onSimpleDialogClick() {
        val dialog = SimpleDialogFragment(
            object : SimpleDialogFragmentListener {
                override fun onItemClick(cor: String) {
                    binding.mainLayout.setBackgroundColor(Color.parseColor(cor))
                    binding.tvSimpleDialog.text = "Cor Selecionada: $cor"
                }
            }
        )
        dialog.show(childFragmentManager, "simpleDialog")
    }

}