package com.efhem.creditscore.domain.usecase

import com.efhem.creditscore.domain.executor.TestPostExecutionThread
import com.efhem.creditscore.fakes.FakeCreditScoreRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetCreditScoreUseCaseTest {

    private val useCase =
        GetCreditScoreUseCase(FakeCreditScoreRepository(), TestPostExecutionThread())

    @Test
    fun `getCreditScoreUseCase returns creditScore`() = runBlockingTest {

        val actual = useCase().first()

        assertThat(actual).isNotNull()
    }
}