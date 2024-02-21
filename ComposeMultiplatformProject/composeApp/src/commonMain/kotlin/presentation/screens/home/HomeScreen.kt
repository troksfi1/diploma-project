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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.repository.FakeRepositoryImpl
import domain.EventCategory
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.composables.EventLazyRow
import presentation.composables.ImageButton
import presentation.composables.NewsItem

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
            Surface {
                Image(
                    painter = painterResource("drawable/img_pribram_znak.jpg"),
                    contentDescription = "pribramLogo",
                    //colorFilter =
                )
            }

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
