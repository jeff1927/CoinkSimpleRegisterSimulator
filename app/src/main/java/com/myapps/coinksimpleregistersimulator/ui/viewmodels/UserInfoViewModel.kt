package com.myapps.coinksimpleregistersimulator.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.coinksimpleregistersimulator.domain.constans.STRING_ERROR_DE_CONEXION
import com.myapps.coinksimpleregistersimulator.domain.constans.STRING_ERROR_DE_SERVIDOR
import com.myapps.coinksimpleregistersimulator.domain.models.UserInfoModel
import com.myapps.coinksimpleregistersimulator.domain.usecases.CredentialsValidation
import com.myapps.coinksimpleregistersimulator.domain.usecases.GetDocumentTypesUseCase
import com.myapps.coinksimpleregistersimulator.domain.usecases.GetGendersTypesUseCase
import com.myapps.coinksimpleregistersimulator.domain.usecases.UserInfoValidationUseCase
import com.myapps.coinksimpleregistersimulator.domain.utils.StateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userInfoValidationUseCase: UserInfoValidationUseCase,
    private val getDocumentTypesUseCase: GetDocumentTypesUseCase,
    private val getGendersTypesUseCase: GetGendersTypesUseCase
): ViewModel() {

    private val _documentTypeList = MutableLiveData<StateResult<List<String>>>()
    val documentTypeList: LiveData<StateResult<List<String>>> = _documentTypeList

    private val _genderList = MutableLiveData<StateResult<List<String>>>()
    val genderList: LiveData<StateResult<List<String>>> = _genderList

    private val _idTypeError = MutableLiveData<Unit>()
    val idTypeError: LiveData<Unit> = _idTypeError

    private val _idError = MutableLiveData<Unit>()
    val idError: LiveData<Unit> = _idError

    private val _idExpeditionDateError = MutableLiveData<Unit>()
    val idExpeditionDateError: LiveData<Unit> = _idExpeditionDateError

    private val _dateOfBirthError = MutableLiveData<Unit>()
    val dateOfBirthError: LiveData<Unit> = _dateOfBirthError

    private val _genderError = MutableLiveData<Unit>()
    val genderError: LiveData<Unit> = _genderError

    private val _emailError = MutableLiveData<Unit>()
    val emailError: LiveData<Unit> = _emailError

    private val _pinError = MutableLiveData<Unit>()
    val pinError: LiveData<Unit> = _pinError

    private val _userInfoOk = MutableLiveData<StateResult<UserInfoModel>>()
    val userInfoOk: LiveData<StateResult<UserInfoModel>> = _userInfoOk

    init {
        getDocumentType()
        getGenderType()
    }

    private fun getDocumentType() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    getDocumentTypesUseCase().collect {
                        _documentTypeList.postValue(it)
                    }
                }catch (e: HttpException) {
                    _documentTypeList.postValue(StateResult.Failed(STRING_ERROR_DE_SERVIDOR))
                }catch (e: Exception) {
                    _documentTypeList.postValue(StateResult.Failed(STRING_ERROR_DE_CONEXION))
                }
            }
        }
    }

    private fun getGenderType() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    getGendersTypesUseCase().collect {
                        _genderList.postValue(it)
                    }
                }catch (e: HttpException) {
                    _genderList.postValue(StateResult.Failed(STRING_ERROR_DE_SERVIDOR))
                }catch (e: Exception) {
                    _genderList.postValue(StateResult.Failed(STRING_ERROR_DE_CONEXION))
                }
            }
        }
    }

    fun validateUserInfo(
        phone: String,
        idType: String,
        id: String,
        idExpeditionDate: String,
        dateOfBirth: String,
        gender: String,
        email: String,
        securityCode: String,
    ) {
        val userInfo = UserInfoModel(phone, idType, id, idExpeditionDate, dateOfBirth, gender, email, securityCode)
        _userInfoOk.value = StateResult.Loading()
        when (userInfoValidationUseCase.registerCredentials(userInfo)){
            is CredentialsValidation.IdTypeError -> _idTypeError.value = Unit
            is CredentialsValidation.IdNumberError -> _idError.value = Unit
            is CredentialsValidation.IdExpeditionDateError -> _idExpeditionDateError.value = Unit
            is CredentialsValidation.DateOfBirthFormatError -> _dateOfBirthError.value = Unit
            is CredentialsValidation.GenderError -> _genderError.value = Unit
            is CredentialsValidation.EmailError -> _emailError.value = Unit
            is CredentialsValidation.PinError -> _pinError.value = Unit
            is CredentialsValidation.SuccessfulValidation-> _userInfoOk.value = StateResult.Success(userInfo)
        }
    }

}