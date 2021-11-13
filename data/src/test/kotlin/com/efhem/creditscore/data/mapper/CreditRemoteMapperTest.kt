package com.efhem.creditscore.data.mapper

import com.efhem.creditscore.data.DummyData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CreditRemoteMapperTest {

    private var creditRemoteMapper = CreditRemoteMapper()

    @Test
    fun `check that mapFromModel returns correct data`(){

        val response = DummyData.creditRemoteResponse
        val credit = creditRemoteMapper.mapFromModel(response)
        assertThat(response.accountIDVStatus).isEqualTo(credit.accountIDVStatus)
        assertThat(response.creditReportInfo.score).isEqualTo(credit.score)
        assertThat(response.creditReportInfo.maxScoreValue).isEqualTo(credit.maxScoreValue)
        assertThat(response.creditReportInfo.hasEverDefaulted).isEqualTo(credit.hasEverDefaulted)
        assertThat(response.personaType).isEqualTo(credit.personaType)

    }
}