package com.myapps.coinksimpleregistersimulator.data.repositoryimpl

import androidx.core.content.ContextCompat.getSystemService
import com.myapps.coinksimpleregistersimulator.data.network.api.CoinkApi
import com.myapps.coinksimpleregistersimulator.domain.repository.CoinkRepository
import com.myapps.coinksimpleregistersimulator.domain.utils.StateResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CoinkRepositoryImp @Inject constructor(private val api: CoinkApi): CoinkRepository {

    override suspend fun getDocumentTypes(): Flow<StateResult<List<String>>> = flow {
        emit(StateResult.Loading())
        try {
            val response = api.getAllDocumentTypes()
            if (response.isSuccessful) {
                val docTypeList = response.body()?.let { List(it.size){ i -> it[i].notation } }
                docTypeList?.let {
                    emit(StateResult.Success(it))
                }
            }
            else {
                emit(StateResult.Failed(response.code().toString()))
            }
        }catch (e: HttpException){
            throw HttpException(api.getAllDocumentTypes())
        }
    }

    override suspend fun getGenders(): Flow<StateResult<List<String>>> = flow {
        emit(StateResult.Loading())
        try {
            val response = api.getAllGenders()
            if (response.isSuccessful) {
                val docTypeList = response.body()?.let { List(it.size){ i -> it[i].name } }
                docTypeList?.let {
                    emit(StateResult.Success(it))
                }
            }
            else {
                emit(StateResult.Failed(response.code().toString()))

            }
        }catch (e: HttpException) {
            throw HttpException(api.getAllGenders())
        }
    }

}
