package com.myapps.coinksimpleregistersimulator.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.myapps.coinksimpleregistersimulator.databinding.FragmentSuccesfullRegistrationBinding
import com.myapps.coinksimpleregistersimulator.domain.utils.navigateWithDirections


class SuccessfullRegistrationFragment : Fragment() {

    private var _binding: FragmentSuccesfullRegistrationBinding? = null
    private val binding get() = _binding!!

    private val args: SuccessfullRegistrationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccesfullRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAccept.setOnClickListener {
            val info = args.userInfo
            Log.i("User info", "Telefono = ${info.phone}, Tipo de documento = ${info.idType}, Numero Documento = ${info.id}, Fecha de Expedicion = ${info.idExpeditionDate}, Fecha de Nacimiento ${info.dateOfBirth}, Genero =  ${info.gender}, Correo = ${info.email}, Pin = ${info.securityPin}" )
            navigateWithDirections(SuccessfullRegistrationFragmentDirections.actionSuccesfullRegistrationFragmentToEntryFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
