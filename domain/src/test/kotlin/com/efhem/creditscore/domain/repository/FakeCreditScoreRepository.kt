package com.efhem.creditscore.domain.repository

import com.efhem.creditscore.domain.models.CreditScoreEntity

class FakeCreditScoreRepository: CreditScoreRepository {

    override suspend fun getCreditScore(): CreditScoreEntity {
        return CreditScoreEntity(
            70,
            100,
            50,
            false,
            "PASS",
            "INEXPERIENCED"
        )
    }
}