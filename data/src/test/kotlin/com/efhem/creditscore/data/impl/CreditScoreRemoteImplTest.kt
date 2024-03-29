package com.efhem.creditscore.data.impl

import com.efhem.creditscore.data.mapper.CreditRemoteMapper
import com.efhem.creditscore.data.utils.REQUEST_PATH
import com.efhem.creditscore.data.utils.RequestDispatcher
import com.efhem.creditscore.data.utils.makeTestApiService
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class CreditScoreRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var creditScoreRepository: CreditScoreRepository
    private val creditRemoteMapper: CreditRemoteMapper = CreditRemoteMapper()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        creditScoreRepository =
            CreditScoreRemoteImpl(makeTestApiService(mockWebServer), creditRemoteMapper)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check that getCreditScore returns data`() = runBlocking {
        val creditScore = creditScoreRepository.getCreditScore()

        assertThat(creditScore).isNotNull()
    }

    @Test
    fun `check that getCreditScore returns correct data`() = runBlocking {
        val creditScore = creditScoreRepository.getCreditScore()

        assertThat(creditScore.accountIDVStatus).isEqualTo("PASS")
        assertThat(creditScore.score).isEqualTo(514)
        assertThat(creditScore.hasEverDefaulted).isFalse()
        assertThat(creditScore.maxScoreValue).isGreaterThan(0)
        assertThat(creditScore.minScoreValue).isEqualTo(0)
    }

    @Test
    fun `check that calling getCreditScore makes request to correct path`() = runBlocking {
        creditScoreRepository.getCreditScore()
        assertThat(REQUEST_PATH).isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling getCreditScore makes a GET request`() = runBlocking {
        creditScoreRepository.getCreditScore()
        assertThat("GET $REQUEST_PATH HTTP/1.1").isEqualTo(mockWebServer.takeRequest().requestLine)
    }


}