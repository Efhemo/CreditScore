package com.efhem.creditscore.domain.repository

import com.efhem.creditscore.domain.models.CreditScore

interface CreditScoreRepository {

    suspend fun getCreditScore(): CreditScore

}