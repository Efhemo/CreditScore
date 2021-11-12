package com.efhem.creditscore.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.efhem.creditscore.R
import com.efhem.creditscore.di.RemoteModule
import com.efhem.creditscore.ui.models.CreditScore
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
    val snackbarHostState = SnackbarHostState()

    var creditScore by remember {
        mutableStateOf<CreditScore?>(null)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    when (uiState) {
        is ViewState.Error -> {
            isLoading = false
            scope.launch {
                snackbarHostState.showSnackbar((uiState as ViewState.Error).throwable.message ?: "")
            }
        }
        is ViewState.Success -> {
            isLoading = false
            creditScore = (uiState as ViewState.Success).creditScore
        }
        is ViewState.Loading -> {
            isLoading = true
            scope.launch {
                snackbarHostState.showSnackbar("Loading")
            }
        }
    }


    Scaffold(
        topBar = {
            CustomAppBar(hideButton = isLoading) {
                homeViewModel.loadCreditScore()
            }
        }
    ) {
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
                        val json =
                            RemoteModule.provideMoshi().adapter(CreditScore::class.java).toJson(it)
                        navController.navigate("details/$json")
                    }
                }
            )

        }

        SnackbarHost(hostState = snackbarHostState)

    }


}

@Composable
fun CustomAppBar(hideButton: Boolean = false, onClickRefresh: () -> Unit) {

    Surface(color = MaterialTheme.colors.primarySurface) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(all = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.subtitle2
                )

                if(!hideButton){
                    IconButton(
                        onClick = onClickRefresh,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = stringResource(R.string.button_descriptive)
                        )
                    }
                }
            }

        }
    }
}


@Preview
@Composable
fun HomeScreenPrev() {
    CustomAppBar {}
}