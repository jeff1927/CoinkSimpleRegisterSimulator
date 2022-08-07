package com.myapps.coinksimpleregistersimulator.data.network.api

import com.myapps.coinksimpleregistersimulator.BuildConfig
import com.myapps.coinksimpleregistersimulator.data.network.dto.DocumentTypeDto
import com.myapps.coinksimpleregistersimulator.data.network.dto.GenderDTO
import com.myapps.coinksimpleregistersimulator.domain.constans.DOCUMENT_TYPE_ENDPOINT
import com.myapps.coinksimpleregistersimulator.domain.constans.GENDER_ENDPOINT
import com.myapps.coinksimpleregistersimulator.domain.constans.QUERY_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinkApi {

    @GET(DOCUMENT_TYPE_ENDPOINT)
    suspend fun getAllDocumentTypes(
        @Query(QUERY_API_KEY)
        apiKey: String = BuildConfig.PUBLIC_API_KEY,
    ): Response<List<DocumentTypeDto>>

    @GET(GENDER_ENDPOINT)
    suspend fun getAllGenders(
        @Query(QUERY_API_KEY)
        apiKey: String = BuildConfig.PUBLIC_API_KEY,
    ): Response<List<GenderDTO>>

}