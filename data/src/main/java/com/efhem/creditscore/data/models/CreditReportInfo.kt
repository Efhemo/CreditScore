package com.efhem.creditscore.data.models

data class CreditReportInfo (
    val score: Int,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val hasEverDefaulted: Boolean
)