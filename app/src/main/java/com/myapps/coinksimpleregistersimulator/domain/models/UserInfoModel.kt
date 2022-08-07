package com.myapps.coinksimpleregistersimulator.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoModel(
    val phone: String,
    val idType: String,
    val id: String,
    val idExpeditionDate: String,
    val dateOfBirth: String,
    val gender: String,
    val email: String,
    val securityPin: String
):Parcelable
