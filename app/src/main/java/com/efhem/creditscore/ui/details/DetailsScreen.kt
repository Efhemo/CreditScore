package com.efhem.creditscore.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.efhem.creditscore.domain.models.CreditScore

@Composable
fun DetailScreen(creditScore: CreditScore){

    Scaffold {

        Surface(
            color = MaterialTheme.colors.background,
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ItemDetail("Score:", creditScore.score.toString())
                ItemDetail("Max Score:", creditScore.maxScoreValue.toString())
                ItemDetail("Account Status:", creditScore.accountIDVStatus)
                ItemDetail("Min Score:", creditScore.minScoreValue.toString())
                ItemDetail("Personal Type:", creditScore.personaType)
            }
        }
    }

}


@Composable
fun ItemDetail(title: String, value: String) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(all = 8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold
        )
    }

}


@Preview
@Composable
fun DetailsScreenPrev() {
    ItemDetail(title = "Payment Type", value = "Experience")
}