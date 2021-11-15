package com.efhem.creditscore.ui.home

import com.efhem.creditscore.executor.TestPostExecutionThread
import com.efhem.creditscore.fakes.FakeCreditScoreRepository
import com.efhem.creditscore.domain.usecase.GetCreditScoreUseCase
import com.efhem.creditscore.ui.mappers.CreditScoreMapper
import com.efhem.creditscore.ui.models.ViewState
import com.efhem.creditscore.utils.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private val creditScoreMapper: CreditScoreMapper = CreditScoreMapper()

    private val getCreditScoreUseCase = GetCreditScoreUseCase(FakeCreditScoreRepository(), TestPostExecutionThread())

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(getCreditScoreUseCase, creditScoreMapper)
    }

    @Test
    fun `observe loadCreditScore for success`() = runBlockingTest {

        val results = mutableListOf<ViewState>()
        val job = launch {
            homeViewModel.uiState.toList(results)
        }
        homeViewModel.loadCreditScore()

        assertThat(results[0] is ViewState.Loading)
        assertThat(results[1] is ViewState.Success)

        job.cancel()

    }

}