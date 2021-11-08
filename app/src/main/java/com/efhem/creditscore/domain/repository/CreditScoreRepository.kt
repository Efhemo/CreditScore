package com.efhem.creditscore.domain.repository

import com.efhem.creditscore.domain.models.CreditScoreEntity

interface CreditScoreRepository {

    suspend fun getCreditScore(): CreditScoreEntity

}