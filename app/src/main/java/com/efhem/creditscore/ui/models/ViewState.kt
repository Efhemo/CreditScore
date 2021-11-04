package com.efhem.creditscore.ui.models

import com.efhem.creditscore.domain.models.CreditScore

sealed class ViewState {
    object Loading : ViewState()
    object Empty : ViewState()
    data class Error(val throwable: Throwable) : ViewState()
    data class Success(val creditScore: CreditScore) : ViewState()
}