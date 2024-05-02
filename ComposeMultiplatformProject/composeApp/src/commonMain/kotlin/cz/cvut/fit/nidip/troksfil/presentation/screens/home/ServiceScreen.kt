package cz.cvut.fit.nidip.troksfil.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

class ServiceScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /*Text(event.title, fontSize = 15.sp)*/ },
                    navigationIcon = {
                        Button(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                )
            },
            bottomBar = { BottomNavigation { } }
        ) { innerPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(
                        PaddingValues(
                            bottom = innerPadding.calculateBottomPadding(),
                            top = innerPadding.calculateTopPadding()
                        )
                    )
            ) {
                WebView(
                    rememberWebViewState(
                        "https://pribram.eu/potrebuji-vyridit.html"
                    )
                )
            }
        }
    }
}