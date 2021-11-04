package com.efhem.creditscore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import com.efhem.creditscore.ui.models.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor (
    private val creditScoreRepository: CreditScoreRepository
): ViewModel(){

    private val _stateControl = Channel<ViewState>(Channel.BUFFERED)
    val stateControl = _stateControl.receiveAsFlow()


    init {
        loadCreditScore()
    }

    private fun loadCreditScore() {
        viewModelScope.launch {
            _stateControl.send(ViewState.Loading)
            kotlin.runCatching {
                withContext(Dispatchers.IO){
                    creditScoreRepository.getCreditScore()
                }
            }.onSuccess {
                println("call loadCredit score value $it")
                _stateControl.send(ViewState.Success(it))
            }.onFailure {
                _stateControl.send(ViewState.Error(it))
            }
        }
    }

}