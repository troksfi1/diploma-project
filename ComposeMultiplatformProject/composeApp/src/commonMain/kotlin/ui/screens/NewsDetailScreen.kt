package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.model.News
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class NewsDetailScreen(private var news: News) : Screen {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
    @Composable
    override fun Content() {

        val navigator: Navigator = LocalNavigator.currentOrThrow

        LazyHorizontalGrid(
            rows = GridCells.Adaptive(minSize = 128.dp)
        ) {

        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(news.title, fontSize = 15.sp) },
                    navigationIcon = {
                        Button(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                //.padding(6.dp)
                //.padding(innerPadding)
            ) {
                Image(
                    modifier = Modifier.height(300.dp).fillMaxWidth(),
                    painter = painterResource(news.coverPhotoPath),
                    contentDescription = "coverPhoto",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = news.title,
                    //fontSize =
                )
                Text(
                    text = news.author,
                    //fontSize =
                )
                Text(
                    text = news.dateTime,
                    //fontSize =
                )
                Text(
                    text = news.text,
                    //fontSize =
                )
            }
        }
    }
}
