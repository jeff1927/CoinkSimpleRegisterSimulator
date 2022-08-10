package com.myapps.coinksimpleregistersimulator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myapps.coinksimpleregistersimulator.databinding.FragmentPhoneNumberBinding
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_0
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_1
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_2
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_3
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_4
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_5
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_6
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_7
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_8
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_9
import com.myapps.coinksimpleregistersimulator.domain.constans.STRING_EMPTY
import com.myapps.coinksimpleregistersimulator.domain.constans.INT_11
import com.myapps.coinksimpleregistersimulator.domain.utils.deleteSpaces
import com.myapps.coinksimpleregistersimulator.domain.utils.navigate
import com.myapps.coinksimpleregistersimulator.ui.viewmodels.PhoneViewModel

class PhoneNumberFragment : Fragment() {

    private var _binding: FragmentPhoneNumberBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhoneViewModel by viewModels()

    private var phoneNumber = MutableList<String>(0){ _ -> ""}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        binding.btnCheck.isEnabled = false
        binding.tvPhoneNumber.text = phoneNumber.joinToString (STRING_EMPTY) { it }
    }

    private fun setupListeners() {
        binding.apply {
            btn0.setOnClickListener {addDigit(INT_0)}
            btn1.setOnClickListener {addDigit(INT_1)}
            btn2.setOnClickListener {addDigit(INT_2)}
            btn3.setOnClickListener {addDigit(INT_3)}
            btn4.setOnClickListener { addDigit(INT_4)}
            btn5.setOnClickListener { addDigit(INT_5)}
            btn6.setOnClickListener { addDigit(INT_6)}
            btn7.setOnClickListener { addDigit(INT_7)}
            btn8.setOnClickListener { addDigit(INT_8)}
            btn9.setOnClickListener { addDigit(INT_9)}
            btnBackspace.setOnClickListener { deleteDigit() }
            btnCheck.setOnClickListener {
                val pNumber = phoneNumber.joinToString (STRING_EMPTY) { it }.deleteSpaces()
                navigate(
                    PhoneNumberFragmentDirections.actionPhoneNumberFragmentToUserInfoFragment(pNumber),
                    null
                )
            }
        }
    }

    private fun addDigit(digit: Int) {
        if (phoneNumber.size < INT_11) {
            phoneNumber.add(digit.toString())
            binding.tvPhoneNumber.text = phoneNumber.joinToString (STRING_EMPTY) { it }
            if (phoneNumber.size == INT_3){
                phoneNumber.add(" ")
            }
        }
        enableButton()
    }

    private fun enableButton() {
        if(phoneNumber.size == INT_11){
            binding.btnCheck.apply {
                isEnabled = true
            }
        }
    }

    private fun deleteDigit() {
        if (phoneNumber.isNotEmpty()){
            phoneNumber.removeLast()
            binding.btnCheck.isEnabled = false
        }
        binding.tvPhoneNumber.text = phoneNumber.joinToString (STRING_EMPTY){ it }
    }

    override fun onResume() {
        super.onResume()
        enableButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}