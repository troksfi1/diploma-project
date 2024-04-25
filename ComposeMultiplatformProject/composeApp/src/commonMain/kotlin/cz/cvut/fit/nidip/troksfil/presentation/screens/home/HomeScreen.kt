package cz.cvut.fit.nidip.troksfil.presentation.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cz.cvut.fit.nidip.troksfil.di.getScreenModel
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.presentation.components.EventItem
import cz.cvut.fit.nidip.troksfil.presentation.components.ImageButton
import cz.cvut.fit.nidip.troksfil.presentation.components.NewsItem
import cz.cvut.fit.nidip.troksfil.presentation.screens.core.EventDetailScreen
import org.jetbrains.compose.resources.DrawableResource
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

        val uriHandler = LocalUriHandler.current

        Scaffold(
            bottomBar = { BottomNavigation {} }
        ) { it ->
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
                    var imageName = ""
                    imageName = if (isSystemInDarkTheme()) {
                        "img_pribram_logo_without_backgroud_dark.png"
                    } else "img_pribram_logo_without_backgroud_light.png"
                    Image(
                        painter = painterResource(DrawableResource("drawable/$imageName")), //img_pribram-logo-white.png
                        contentDescription = "pribramLogo",
                        //colorFilter =
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {
                    /*state.usedImageButtons.forEach {
                        ImageButton(it.name)
                    }*/
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
                            uriHandler.openUri("https://www.youtube.com/@mestopribram1671")
                        }
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Populární události",
                        modifier = Modifier
                            .padding(10.dp),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Row(modifier = Modifier.align(Alignment.CenterVertically).padding(10.dp))
                    {
                        if (state.isFetchingNews) {
                            Text(
                                "Aktualizuji události",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelMedium
                            )
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                //todo refactor
                val events = state.events.collectAsState()
                val predicate: (EventCategory) -> Boolean = { it == EventCategory.MUSIC }
                val eventsList = events.value.filter { event -> event.categories.any(predicate) }
                LazyRow {
                    items(eventsList) { event ->  //state.filteredEvents
                        EventItem(event = event, onItemClick = {
                            navigator.push(EventDetailScreen(event))
                        })
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Aktuality",
                        modifier = Modifier
                            .padding(10.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(modifier = Modifier.align(Alignment.CenterVertically).padding(10.dp))
                    {
                        if (state.isFetchingNews) {
                            Text(
                                "Aktualizuji novinky",
                                modifier = Modifier
                                    .padding(5.dp),
                                style = MaterialTheme.typography.labelMedium
                            )
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                val news = state.news.collectAsState()
                LazyColumn {
                    items(news.value) { news ->
                        NewsItem(news = news, onItemClick = {
                            navigator.push(NewsDetailScreen(news))
                        })
                    }
                }
            }
        }
    }
}
