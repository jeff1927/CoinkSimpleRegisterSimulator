package com.myapps.coinksimpleregistersimulator.di

import com.myapps.coinksimpleregistersimulator.domain.repository.CoinkRepository
import com.myapps.coinksimpleregistersimulator.domain.usecases.GetDocumentTypesUseCase
import com.myapps.coinksimpleregistersimulator.domain.usecases.GetGendersTypesUseCase
import com.myapps.coinksimpleregistersimulator.domain.usecases.UserInfoValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun providesUserInfoValidationUseCase(): UserInfoValidationUseCase {
        return UserInfoValidationUseCase()
    }

    @Singleton
    @Provides
    fun providesGetDocumentTypesUseCase(repository: CoinkRepository): GetDocumentTypesUseCase {
        return GetDocumentTypesUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesGetGendersTypesUseCase(repository: CoinkRepository): GetGendersTypesUseCase {
        return GetGendersTypesUseCase(repository)
    }
}