package cz.cvut.fit.nidip.troksfil.presentation.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cz.cvut.fit.nidip.troksfil.di.getScreenModel
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.presentation.components.EventLazyRow
import cz.cvut.fit.nidip.troksfil.presentation.components.ImageButton
import cz.cvut.fit.nidip.troksfil.presentation.components.NewsItem
import cz.cvut.fit.nidip.troksfil.presentation.screens.events.EventsState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class HomeScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    //@Preview
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()
        val scrollState = rememberScrollState()
        val navigator: Navigator = LocalNavigator.currentOrThrow

        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf("Loading") }

        Scaffold(
            bottomBar = { BottomNavigation {} }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(PaddingValues(bottom = it.calculateBottomPadding())),
                //.verticalScroll(state = scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth().size(200.dp)
                ) {
                    Image(
                        painter = painterResource("drawable/img_pribram_znak.jpg"), //img_pribram-logo-white.png
                        contentDescription = "pribramLogo",
                        //colorFilter =
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {
                    ImageButton(name = "Úřad", imagePath = "drawable/img_municipal_authority.png",
                        onClick = {
                            //todo replace by VM impl?
                        }
                    )
                    ImageButton(name = "Závady", imagePath = "drawable/img_defect.png",
                        onClick = { navigator.push(DefectScreen()) } //todo replace by VM impl?
                    )


                    ImageButton(name = "Služby", imagePath = "drawable/img_service.png",
                        onClick = { } //todo replace by VM impl?
                    )
                    ImageButton(name = "YouTube", imagePath = "drawable/img_youtube.png",
                        onClick = {
                            //screenModel.onEvent(HomeEvent.OnButtonYouTubeClicked(LocalUriHandler.current))
                        }
                    )
                }


                LaunchedEffect(true) {
                    scope.launch {

                        screenModel.onEvent(HomeEvent.OnInit)


                        /*text = try {


                            //Greeting().greeting()
                        } catch (e: Exception) {
                            e.localizedMessage?.let { Logger.e(it.toString()) }
                            e.localizedMessage ?: "error"
                        }*/
                    }
                }

                Text(
                    state.news.toString(),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    "Události",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.titleLarge
                )
                EventLazyRow(EventCategory.TOP_EVENTS, EventsState())
                Text(
                    "Aktuality",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.titleLarge
                )

                LazyColumn {
                    items(state.news) { news ->
                        NewsItem(news = news, onItemClick = {
                            navigator.push(NewsDetailScreen(news))
                        })
                    }
                }
            }
        }
    }
}
