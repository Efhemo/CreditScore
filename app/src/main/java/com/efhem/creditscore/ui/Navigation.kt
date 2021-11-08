package com.efhem.creditscore.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.efhem.creditscore.ui.models.CreditScore
import com.efhem.creditscore.ui.details.DetailScreen
import com.efhem.creditscore.ui.home.HomeScreen
import com.efhem.creditscore.utils.AssetParamType

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable(
            "details/{creditScore}",
            arguments = listOf(
                navArgument("creditScore") {
                    type = AssetParamType()
                }
            )
        ) {
            val creditScore = it.arguments?.getParcelable<CreditScore>("creditScore")
            if (creditScore != null) {
                DetailScreen(creditScore)
            }
        }
    }
}