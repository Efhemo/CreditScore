package com.efhem.creditscore.fakes

import com.efhem.creditscore.domain.models.CreditScoreEntity
import com.efhem.creditscore.domain.repository.CreditScoreRepository

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