package com.myapps.coinksimpleregistersimulator.domain.repository

import com.myapps.coinksimpleregistersimulator.domain.utils.StateResult
import kotlinx.coroutines.flow.Flow

interface CoinkRepository {

    suspend fun getDocumentTypes():Flow<StateResult<List<String>>>

    suspend fun getGenders():Flow<StateResult<List<String>>>
}