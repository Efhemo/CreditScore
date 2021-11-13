package com.efhem.creditscore.domain.models


data class CreditScoreEntity(
    val score: Int,
    val maxScoreValue: Int,
    val minScoreValue: Int,
    val hasEverDefaulted: Boolean,
    val accountIDVStatus: String,
    val personaType: String
)