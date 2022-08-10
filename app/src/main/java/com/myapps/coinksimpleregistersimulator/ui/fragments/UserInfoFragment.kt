package com.myapps.coinksimpleregistersimulator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.myapps.coinksimpleregistersimulator.R
import com.myapps.coinksimpleregistersimulator.databinding.FragmentUserInfoBinding
import com.myapps.coinksimpleregistersimulator.domain.utils.StateResult
import com.myapps.coinksimpleregistersimulator.domain.utils.deleteSpaces
import com.myapps.coinksimpleregistersimulator.domain.utils.hideProgressBar
import com.myapps.coinksimpleregistersimulator.domain.utils.navigateWithDirections
import com.myapps.coinksimpleregistersimulator.domain.utils.showProgressBar
import com.myapps.coinksimpleregistersimulator.domain.utils.showSnackBar
import com.myapps.coinksimpleregistersimulator.ui.viewmodels.UserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserInfoViewModel by viewModels()

    private val args: UserInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
        setupDropDownMenus()
    }

    private fun setupDropDownMenus() {
        viewModel.documentTypeList.observe(viewLifecycleOwner){
            when(it){
                is StateResult.Loading -> {
                    binding.progressBar.showProgressBar()
                }
                is StateResult.Failed -> {
                    binding.progressBar.hideProgressBar()
                    binding.etDocumentType.error = it.message}
                is StateResult.Success -> {
                    binding.progressBar.hideProgressBar()
                    it.data?.let { items ->
                        val adapter = ArrayAdapter(requireContext(),R.layout.option_item,items)
                        binding.etDocumentType.setAdapter(adapter)
                    }
                }
            }
        }
        viewModel.genderList.observe(viewLifecycleOwner){
            when(it){
                is StateResult.Loading -> {
                    binding.progressBar.showProgressBar()
                }
                is StateResult.Failed -> {
                    binding.progressBar.hideProgressBar()
                    binding.etGender.error = it.message
                }
                is StateResult.Success -> {
                    binding.progressBar.hideProgressBar()
                    it.data?.let { items ->
                        val adapter = ArrayAdapter(requireContext(),R.layout.option_item_genders,items)
                        binding.etGender.setAdapter(adapter)
                    }
                }
            }
        }
    }

    private fun setupObservers() {
        with(viewModel){
            idTypeError.observe(viewLifecycleOwner){
                binding.etDocumentType.error = getString(R.string.error_en_el_tipo_de_documento)
                showSnackBar(getString(R.string.seleccione_una_de_las_opciones))
            }
            idError.observe(viewLifecycleOwner){
                binding.etDucumentNumber.error = getString(R.string.documento_invalido)
                showSnackBar(getString(R.string.documento_invalido))
            }
            idExpeditionDateError.observe(viewLifecycleOwner){
                binding.etExpeditionDate.error = getString(R.string.error_en_la_fecha_de_expedicion)
                showSnackBar(getString(R.string.formato_no_valido))
            }
            dateOfBirthError.observe(viewLifecycleOwner){
                binding.etDateOfBirth.error = getString(R.string.error_en_la_fecha_de_nacimiento)
                showSnackBar(getString(R.string.formato_no_valido))
            }
            genderError.observe(viewLifecycleOwner){
                binding.etGender.error = getString(R.string.error_tipo_de_genero)
                showSnackBar(getString(R.string.elija_un_tipo_de_genero))
            }
            emailError.observe(viewLifecycleOwner){
                binding.etEmail.error = getString(R.string.correo_no_valido)
                showSnackBar(getString(R.string.correo_no_valido))
            }
            pinError.observe(viewLifecycleOwner){
                binding.etSecurityPin.error = getString(R.string.pin_no_valido)
                showSnackBar(getString(R.string.pin_no_valido))
            }
            userInfoOk.observe(viewLifecycleOwner){ userInfo ->
                userInfo.data?.let {
                    if (compareEmail() && comparePin()){
                    navigateWithDirections(UserInfoFragmentDirections.actionUserInfoFragmentToContractFragment(it))
                    }
                    else{
                        if (!compareEmail()) binding.etConfirmEmail.error = getString(R.string.correo_no_coincide)
                        else{
                            if (!comparePin()) binding.etConfirmSecurityPin.error = getString(R.string.pin_no_coincide)
                        }
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding){
            btnNext.setOnClickListener {
                viewModel.validateUserInfo(
                    args.phoneNumber,
                    etDocumentType.text.toString().deleteSpaces(),
                    etDucumentNumber.text.toString().deleteSpaces(),
                    etExpeditionDate.text.toString().deleteSpaces(),
                    etDateOfBirth.text.toString().deleteSpaces(),
                    etGender.text.toString().deleteSpaces(),
                    etEmail.text.toString().deleteSpaces(),
                    etSecurityPin.text.toString().deleteSpaces()
                )
            }
        }
    }

    private fun compareEmail(): Boolean {
        var isCorrect = false
        val email = binding.etEmail.text.toString().deleteSpaces()
        val emailConfirmation = binding.etConfirmEmail.text.toString().deleteSpaces()
        if (emailConfirmation.isNotEmpty() && email == emailConfirmation){
            isCorrect = true
        }
        return isCorrect
    }

    private fun comparePin(): Boolean {
        var isCorrect = false
        val pin = binding.etSecurityPin.text.toString().deleteSpaces()
        val pinConfirmation = binding.etConfirmSecurityPin.text.toString().deleteSpaces()
        if (pinConfirmation.isNotEmpty() && pin == pinConfirmation){
            isCorrect = true
        }
        return isCorrect
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
