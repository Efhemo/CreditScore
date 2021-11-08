package com.efhem.creditscore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.creditscore.domain.models.CreditScore
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import com.efhem.creditscore.ui.models.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val creditScoreRepository: CreditScoreRepository
): ViewModel(){

    private val _uiState = MutableStateFlow<ViewState>(ViewState.Success(CreditScore(0, 100)))
    val uiState: StateFlow<ViewState>  = _uiState


    init {
        loadCreditScore()
    }

    fun loadCreditScore() {
        _uiState.value = ViewState.Loading
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO){
                    creditScoreRepository.getCreditScore()
                }
            }.onSuccess {
                _uiState.value = ViewState.Success(it)
            }.onFailure {
                _uiState.value = ViewState.Error(it)
            }
        }
    }

}