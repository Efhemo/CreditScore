package com.efhem.creditscore.domain.repository

import com.efhem.creditscore.domain.model.CreditScore

interface CreditScoreRepository {

    suspend fun getCreditScore(): CreditScore

}