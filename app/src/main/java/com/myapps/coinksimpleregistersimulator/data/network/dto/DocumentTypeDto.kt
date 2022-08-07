package com.myapps.coinksimpleregistersimulator.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DocumentTypeDto(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("notation")
    val notation: String,
    @Expose
    @SerializedName("description")
    val description: String
)
