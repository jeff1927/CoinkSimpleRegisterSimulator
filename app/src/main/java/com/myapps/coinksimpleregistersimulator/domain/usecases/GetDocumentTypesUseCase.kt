package com.myapps.coinksimpleregistersimulator.domain.usecases

import com.myapps.coinksimpleregistersimulator.domain.repository.CoinkRepository
import javax.inject.Inject

class GetDocumentTypesUseCase @Inject constructor(private val repository: CoinkRepository) {

    suspend operator fun invoke() = repository.getDocumentTypes()
}