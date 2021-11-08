package com.efhem.creditscore.data.mapper

import com.efhem.creditscore.data.mapper.base.RemoteModelMapper
import com.efhem.creditscore.data.models.CreditRemoteResponse
import com.efhem.creditscore.domain.models.CreditScoreEntity
import javax.inject.Inject

class CreditRemoteMapper @Inject constructor() :
    RemoteModelMapper<CreditRemoteResponse, CreditScoreEntity> {


    override fun mapFromModel(model: CreditRemoteResponse): CreditScoreEntity {
        return with(model) {
            CreditScoreEntity(
                creditReportInfo.score,
                creditReportInfo.maxScoreValue,
                creditReportInfo.minScoreValue,
                creditReportInfo.hasEverDefaulted,
                accountIDVStatus, personaType
            )
        }
    }

}