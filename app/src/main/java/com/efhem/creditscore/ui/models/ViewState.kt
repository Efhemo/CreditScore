package com.efhem.creditscore.ui.models

sealed class ViewState {
    object Loading : ViewState()
    data class Error(val throwable: Throwable) : ViewState()
    data class Success(val creditScore: CreditScore) : ViewState()
}