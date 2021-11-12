package com.efhem.creditscore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.creditscore.domain.mapper.CreditScoreEntityMapper
import com.efhem.creditscore.ui.models.CreditScore
import com.efhem.creditscore.domain.usecase.GetCreditScoreUseCase
import com.efhem.creditscore.ui.models.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCreditScoreUseCase: GetCreditScoreUseCase,
    private val mapper: CreditScoreEntityMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewState>(ViewState.Success(CreditScore(0, 100)))
    val uiState: StateFlow<ViewState> = _uiState


    init {
        loadCreditScore()
    }

    fun loadCreditScore() {
        viewModelScope.launch {
            getCreditScoreUseCase()
                .onStart { _uiState.value = ViewState.Loading }
                .catch { _uiState.value = ViewState.Error(it) }
                .collect {
                    _uiState.value = ViewState.Success(mapper.mapFromModel(it))
                }
        }
    }

}