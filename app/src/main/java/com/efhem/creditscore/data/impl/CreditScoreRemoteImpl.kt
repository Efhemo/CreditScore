package com.efhem.creditscore.data.impl

import com.efhem.creditscore.data.ApiService
import com.efhem.creditscore.data.mapper.CreditRemoteMapper
import com.efhem.creditscore.data.models.CreditRemoteResponse
import com.efhem.creditscore.domain.models.CreditScore
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import javax.inject.Inject

class CreditScoreRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val creditRemoteMapper: CreditRemoteMapper
) : CreditScoreRepository {

    override suspend fun getCreditScore(): CreditScore {
        val subjects: CreditRemoteResponse = apiService.getData()
        return creditRemoteMapper.mapFromModel(subjects)
    }

}