package presentation.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.repository.FakeRepositoryImpl
import domain.EventCategory
import domain.model.News
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.xml.xml
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.composables.EventLazyRow
import presentation.composables.ImageButton
import presentation.composables.NewsItem
import presentation.screens.core.DefectScreen

class HomeScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    //@Preview
    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        val navigator: Navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize(),
            //.verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().size(200.dp)
            ) {
                Image(
                    painter = painterResource("drawable/imgpribram-logo-white.png"),
                    contentDescription = "pribramLogo",
                    //colorFilter =
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {

                /*Button(onClick = {}, content = {}) {
                    Image(
                        painter = painterResource("drawable/img_municipal_authority.png"),
                        contentDescription = "Favorite",
                    )
                }*/

                val uriHandler = LocalUriHandler.current

                ImageButton(name = "Úřad", imagePath = "drawable/img_municipal_authority.png",
                    onClick = {

                    }
                )
                ImageButton(name = "Závady", imagePath = "drawable/img_defect.png",
                    onClick = { navigator.push(DefectScreen()) }
                )

                val client = HttpClient {
                    install(ContentNegotiation) {
                        xml(
                            contentType = ContentType.Application.Xml,
                            format = XML { //ContentType.Application.Rss  // XML = DefaultXml
                                xmlDeclMode = XmlDeclMode.Auto
                            })
                    }
                }

                suspend fun vdo(): News {
                    val news: News = client.get("https://pribram.eu/xmldata/aktuality/").body()
                    print(news)
                    return news
                }


                ImageButton(name = "Služby", imagePath = "drawable/img_service.png",
                    onClick = {
                        suspend {
                            val news = vdo()
                            val n = news
                        }
                    }
                )
                ImageButton(name = "YouTube", imagePath = "drawable/img_youtube.png",
                    onClick = {
                        uriHandler.openUri("https://www.youtube.com/@mestopribram1671")
                    }
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                "Události",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.titleLarge
            )
            EventLazyRow(EventCategory.MUSIC)
            Text(
                "Aktuality",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.titleLarge
            )

            LazyColumn {

                val newsList = FakeRepositoryImpl.getAllNews()    // todo replace by viewModel impl

                items(newsList) { news ->
                    NewsItem(news = news, onItemClick = { selectedNews ->
                        navigator.push(NewsDetailScreen(news))
                    })
                }
            }
        }
    }
}
