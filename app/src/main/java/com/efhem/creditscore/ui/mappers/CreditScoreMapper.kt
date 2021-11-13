package com.efhem.creditscore.ui.mappers

import com.efhem.creditscore.domain.models.CreditScoreEntity
import com.efhem.creditscore.ui.models.CreditScore
import javax.inject.Inject

class CreditScoreMapper @Inject constructor() {

    fun mapFromModel(model: CreditScoreEntity): CreditScore {
        return with(model) {
            CreditScore(
                model.score,
                model.maxScoreValue,
                model.minScoreValue,
                model.hasEverDefaulted,
                accountIDVStatus, personaType
            )
        }
    }

}