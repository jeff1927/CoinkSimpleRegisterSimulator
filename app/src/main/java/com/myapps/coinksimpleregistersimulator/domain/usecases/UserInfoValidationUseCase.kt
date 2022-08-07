package com.myapps.coinksimpleregistersimulator.domain.usecases

import com.myapps.coinksimpleregistersimulator.domain.models.UserInfoModel
import com.myapps.coinksimpleregistersimulator.domain.utils.isDateFormatValid
import com.myapps.coinksimpleregistersimulator.domain.utils.isEmailNoValid
import javax.inject.Inject

class UserInfoValidationUseCase @Inject constructor() {

    fun registerCredentials(userCredentials: UserInfoModel): CredentialsValidation {
        with(userCredentials) {
            return when {
                idType.isBlank() -> {
                    CredentialsValidation.IdTypeError
                }
                id.isBlank() -> {
                    CredentialsValidation.IdNumberError
                }
                (!idExpeditionDate.isDateFormatValid()) -> {
                    CredentialsValidation.IdExpeditionDateError
                }
                (!dateOfBirth.isDateFormatValid()) -> {
                    CredentialsValidation.DateOfBirthFormatError
                }
                gender.isBlank() -> {
                    CredentialsValidation.GenderError
                }
                email.isEmailNoValid() -> {
                    CredentialsValidation.EmailError
                }
                securityPin.isBlank()-> {
                    CredentialsValidation.PinError
                }

                else -> CredentialsValidation.SuccessfulValidation
            }
        }
    }
}

sealed class CredentialsValidation {

    object IdTypeError : CredentialsValidation()
    object IdNumberError : CredentialsValidation()
    object IdExpeditionDateError : CredentialsValidation()
    object DateOfBirthFormatError : CredentialsValidation()
    object GenderError : CredentialsValidation()
    object EmailError : CredentialsValidation()
    object PinError : CredentialsValidation()

    object SuccessfulValidation : CredentialsValidation()

}