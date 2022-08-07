package com.myapps.coinksimpleregistersimulator.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.myapps.coinksimpleregistersimulator.databinding.FragmentContractBinding
import com.myapps.coinksimpleregistersimulator.domain.utils.navigate


class ContractFragment : Fragment() {

    private var _binding: FragmentContractBinding? = null
    private val binding get() = _binding!!

    private val args: ContractFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContractBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.cbReadContract.setOnCheckedChangeListener { compoundButton, _ ->
            binding.btnAccept.isEnabled = compoundButton.isChecked
        }
        binding.btnAccept.setOnClickListener {
            navigate(ContractFragmentDirections.actionContractFragmentToSuccesfullRegistrationFragment(args.userInfo),null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}