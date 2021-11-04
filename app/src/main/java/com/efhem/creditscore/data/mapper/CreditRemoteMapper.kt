package com.efhem.creditscore.data.mapper

import com.efhem.creditscore.data.mapper.base.RemoteModelMapper
import com.efhem.creditscore.data.model.CreditRemoteResponse
import com.efhem.creditscore.domain.model.CreditScore
import javax.inject.Inject

class CreditRemoteMapper @Inject constructor() :
    RemoteModelMapper<CreditRemoteResponse, CreditScore> {


    override fun mapFromModel(model: CreditRemoteResponse): CreditScore {
        return with(model) {
            CreditScore(
                creditReportInfo.score,
                creditReportInfo.maxScoreValue,
                creditReportInfo.minScoreValue,
                creditReportInfo.hasEverDefaulted,
                accountIDVStatus, creditReportInfo, personaType
            )
        }
    }

}