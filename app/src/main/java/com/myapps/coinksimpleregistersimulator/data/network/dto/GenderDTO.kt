package com.myapps.coinksimpleregistersimulator.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenderDTO(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("description")
    val description: String
)
