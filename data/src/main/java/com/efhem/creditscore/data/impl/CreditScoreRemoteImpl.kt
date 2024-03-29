package com.efhem.creditscore.data.impl

import com.efhem.creditscore.data.ApiService
import com.efhem.creditscore.data.mapper.CreditRemoteMapper
import com.efhem.creditscore.data.models.CreditRemoteResponse
import com.efhem.creditscore.domain.executor.PostExecutionThread
import com.efhem.creditscore.domain.models.CreditScoreEntity
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreditScoreRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val creditRemoteMapper: CreditRemoteMapper
) : CreditScoreRepository {

    override suspend fun getCreditScore(): CreditScoreEntity {
        val subjects: CreditRemoteResponse = apiService.getData()
        return creditRemoteMapper.mapFromModel(subjects)
    }

}