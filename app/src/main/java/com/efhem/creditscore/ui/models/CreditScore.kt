package com.efhem.creditscore.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreditScore(
    val score: Int,
    val maxScoreValue: Int,
    val minScoreValue: Int = 0,
    val hasEverDefaulted: Boolean = false,
    val accountIDVStatus: String = "PASS",
    val personaType: String = "INEXPERIENCE"
): Parcelable