package com.efhem.creditscore.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.creditscore.domain.models.CreditScore
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import com.efhem.creditscore.ui.models.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val creditScoreRepository: CreditScoreRepository
): ViewModel(){

    private val _stateControl = Channel<ViewState>(Channel.BUFFERED)
    val stateControl = _stateControl.receiveAsFlow()

    private val _uiState = MutableStateFlow<ViewState>(ViewState.Success(CreditScore(0, 100)))
    val uiState: StateFlow<ViewState>  = _uiState


    init {
        loadCreditScore()
    }

    private fun loadCreditScore() {
        viewModelScope.launch {
            _stateControl.send(ViewState.Loading)
            _uiState.value = ViewState.Loading
            kotlin.runCatching {
                withContext(Dispatchers.IO){
                    creditScoreRepository.getCreditScore()
                }
            }.onSuccess {
                _stateControl.send(ViewState.Success(it))
                _uiState.value = ViewState.Success(it)
            }.onFailure {
                _stateControl.send(ViewState.Error(it))
                _uiState.value = ViewState.Error(it)
            }
        }
    }

}