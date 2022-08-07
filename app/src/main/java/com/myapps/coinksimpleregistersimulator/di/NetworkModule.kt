package com.myapps.coinksimpleregistersimulator.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapps.coinksimpleregistersimulator.data.network.api.CoinkApi
import com.myapps.coinksimpleregistersimulator.data.repositoryimpl.CoinkRepositoryImp
import com.myapps.coinksimpleregistersimulator.domain.constans.BASE_URL
import com.myapps.coinksimpleregistersimulator.domain.repository.CoinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl() : String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun provideGsonBuilder() : Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideConvertFactory(gson: Gson) : Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Singleton
    @Provides
    fun provideClient(logging: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinkApiService(retrofit: Retrofit) : CoinkApi{
        return retrofit.create(CoinkApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCoinkRepository(api: CoinkApi):CoinkRepository{
        return CoinkRepositoryImp(api)
    }

}