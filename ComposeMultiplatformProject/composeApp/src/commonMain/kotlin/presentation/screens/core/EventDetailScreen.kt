package presentation.screens.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import domain.model.Event
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class EventDetailScreen(private var event: Event) : Screen {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
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
            bottomBar = {

            }
        ) { innerPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier.verticalScroll(state = scrollState)
                //.padding(6.dp)
                //.padding(innerPadding)
            ) {
                Image(
                    modifier = Modifier.height(300.dp).fillMaxWidth().shadow(10.dp),
                    painter = painterResource(event.coverPhotoPath),
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
                        text = event.dateTime,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
