package com.efhem.creditscore.data

import com.efhem.creditscore.data.models.CreditRemoteResponse
import com.efhem.creditscore.data.models.CreditReportInfo

internal object DummyData {

    private val creditReportInfo = CreditReportInfo(
        400,
        700,
        0,
        false
    )

    val creditRemoteResponse = CreditRemoteResponse(
        "PASS",
        creditReportInfo,
        "INEXPERIENCED"
    )


}
