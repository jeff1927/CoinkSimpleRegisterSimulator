package com.myapps.coinksimpleregistersimulator.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myapps.coinksimpleregistersimulator.R
import com.myapps.coinksimpleregistersimulator.databinding.FragmentPhoneNumberBinding
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
        binding.tvPhoneNumber.text = phoneNumber.joinToString (separator = "") { it }
    }

    private fun setupListeners() {
        binding.apply {
            btn0.setOnClickListener {addDigit(0)}
            btn1.setOnClickListener {addDigit(1)}
            btn2.setOnClickListener {addDigit(2)}
            btn3.setOnClickListener {addDigit(3)}
            btn4.setOnClickListener { addDigit(4)}
            btn5.setOnClickListener { addDigit(5)}
            btn6.setOnClickListener { addDigit(6)}
            btn7.setOnClickListener { addDigit(7)}
            btn8.setOnClickListener { addDigit(8)}
            btn9.setOnClickListener { addDigit(9)}
            btnBackspace.setOnClickListener { deleteDigit() }
            btnCheck.setOnClickListener {
                val pNumber = phoneNumber.joinToString (separator = "") { it }.deleteSpaces()
                navigate(
                    PhoneNumberFragmentDirections.actionPhoneNumberFragmentToUserInfoFragment(pNumber),
                    null
                )
            }
        }
    }

    private fun addDigit(digit: Int) {
        if (phoneNumber.size < 11) {
            phoneNumber.add(digit.toString())
            binding.tvPhoneNumber.text = phoneNumber.joinToString (separator = "") { it }
            if (phoneNumber.size == 3){
                phoneNumber.add(" ")
            }
        }
        enableButton()
    }

    private fun enableButton() {
        if(phoneNumber.size == 11){
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
        binding.tvPhoneNumber.text = phoneNumber.joinToString (separator = ""){ it }
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