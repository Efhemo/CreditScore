package com.efhem.creditscore.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.efhem.creditscore.domain.models.CreditScore

@Composable
fun DetailScreen(creditScore: CreditScore){

    Scaffold {
        Column {
            Text(text = creditScore.score.toString())
            Text(text = creditScore.maxScoreValue.toString())
        }
    }

}