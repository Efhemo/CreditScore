package com.efhem.creditscore.domain.model

import com.efhem.creditscore.data.model.CreditReportInfo

data class CreditScore(
    val score: Int,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val hasEverDefaulted: Boolean,
    val accountIDVStatus: String,
    val creditReportInfo: CreditReportInfo,
    val personaType: String
)