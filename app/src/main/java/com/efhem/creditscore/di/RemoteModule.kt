package com.efhem.creditscore.di

import com.efhem.creditscore.data.ApiService
import com.efhem.creditscore.data.ApiServiceFactory
import com.efhem.creditscore.data.impl.CreditScoreRemoteImpl
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @[Provides Singleton]
    fun provideApiService(moshi: Moshi): ApiService =
        ApiServiceFactory.createApiService(moshi)

    @[Provides Singleton]
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @[Provides Singleton]
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}