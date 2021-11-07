package com.efhem.creditscore.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.efhem.creditscore.R
import com.efhem.creditscore.di.RemoteModule
import com.efhem.creditscore.domain.models.CreditScore
import com.efhem.creditscore.ui.components.DonutView
import com.efhem.creditscore.ui.models.ViewState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by homeViewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var creditScore by remember {
        mutableStateOf<CreditScore?>(null)
    }

    when(uiState){
        is ViewState.Error -> scope.launch {
            snackbarHostState.showSnackbar((uiState as ViewState.Error).throwable.message ?: "")
        }
        is ViewState.Success -> {
            creditScore = (uiState as ViewState.Success).creditScore
        }
        is ViewState.Loading -> {
            println("Loading")
        }
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(R.drawable.cbimage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        DonutView(
            indicatorValue = creditScore?.score ?: 0,
            maxIndicatorValue = creditScore?.maxScoreValue ?: 100,
            onClick = {
                creditScore?.let {
                    val json = RemoteModule.provideMoshi().adapter(CreditScore::class.java).toJson(it)
                    navController.navigate("details/$json")
                }
            }
        )

    }

    SnackbarHost(hostState = snackbarHostState)


}