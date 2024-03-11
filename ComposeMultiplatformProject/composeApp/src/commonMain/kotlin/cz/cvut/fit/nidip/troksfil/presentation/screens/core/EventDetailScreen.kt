package cz.cvut.fit.nidip.troksfil.presentation.screens.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import cz.cvut.fit.nidip.troksfil.domain.model.Event

class EventDetailScreen(private var event: Event) : Screen {

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
            bottomBar = { BottomNavigation {} }
        ) { innerPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState)
                    .padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
            ) {
                AsyncImage(
                    model = event.imageUri, // replace with working URL
                    modifier = Modifier.height(300.dp).fillMaxWidth().shadow(10.dp),
                    contentDescription = "coverPhoto",
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "Od: " + event.startDateTime.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Do: " + event.endDateTime.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(Modifier.height(10.dp))

                    WebView(
                        rememberWebViewStateWithHTMLData(
                            data = event.description.trimIndent()
                        )
                    )

                    /*Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyMedium
                    )*/
                }
            }
        }
    }
}
