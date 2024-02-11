package ui.screens.home


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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.model.News
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.composables.EventLazyRow
import ui.composables.ImageButton
import ui.composables.NewsItem
import ui.screens.NewsDetailScreen

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
            Image(
                painter = painterResource("drawable/img_pribram_znak.jpg"),
                contentDescription = "Sample",
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

                ) {
                ImageButton("Úřad", "drawable/img_municipal_authority.png")
                ImageButton("Závady", "drawable/img_defect.png")
                ImageButton("Služby", "drawable/img_service.png")
                ImageButton("YouTube", "drawable/img_youtube.png")
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                "Události",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                fontSize = 20.sp
            )
            EventLazyRow()
            Text(
                "Aktuality",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Start),
                fontSize = 20.sp
            )

            LazyColumn {
                val newsList = listOf(
                    News(
                        1,
                        "st 14.1.2024 20:00",
                        "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                        "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                        "Petr",
                        "drawable/img_news_1.jpg"
                    ),
                    News(
                        2,
                        "st 14.1.2024 20:00",
                        "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                        "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                        "Honza",
                        "drawable/img_news_2.jpg"
                    ),
                    News(
                        3,
                        "st 14.1.2024 20:00",
                        "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                        "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                        "Petr",
                        "drawable/img_news_1.jpg"
                    ),
                    News(
                        4,
                        "st 14.1.2024 20:00",
                        "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                        "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                        "Petr",
                        "drawable/img_news_1.jpg"
                    ),

                    )
                items(newsList) { news ->
                    NewsItem(news = news, onItemClick = { selectedNews ->
                        navigator.push(NewsDetailScreen(news))
                    })
                }
            }
        }
    }
}
