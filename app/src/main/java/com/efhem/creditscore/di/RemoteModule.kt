package com.efhem.creditscore.di

import com.efhem.creditscore.BuildConfig
import com.efhem.creditscore.data.ApiService
import com.efhem.creditscore.data.ApiServiceFactory
import com.efhem.creditscore.utils.JSONObjectAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        ApiServiceFactory.createApiService(moshi, BuildConfig.DEBUG)

    @[Provides Singleton]
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).add(JSONObjectAdapter()).build()

    @[Provides Singleton]
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}