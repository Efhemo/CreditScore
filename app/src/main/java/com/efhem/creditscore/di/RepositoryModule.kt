package com.efhem.creditscore.di

import com.efhem.creditscore.data.impl.CreditScoreRemoteImpl
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @get:[Binds Singleton]
    val CreditScoreRemoteImpl.bindCreditScoreRepository: CreditScoreRepository
}