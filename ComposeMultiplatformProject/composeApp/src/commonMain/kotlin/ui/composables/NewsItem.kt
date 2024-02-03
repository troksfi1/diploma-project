package ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NewsItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(6.dp)
        /*.clickable(onClick =
        Navigator(screen = EventsScreen()) { navigator ->
            SlideTransition(navigator = navigator)
        }) { }*/,

        horizontalArrangement = Arrangement.Start
    ) {
        //Spacer(Modifier.width(6.dp))
        Image(
            painter = painterResource("drawable/img_news_1.jpg"),
            contentDescription = "Sample",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .aspectRatio(4f / 3f)
                .size(10.dp)
        )
        Spacer(Modifier.width(6.dp))
        Column {
            Text(
                "Lávka ve Špitálské je dokončena, další se budou realizovat na jaře",
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 15.sp,
                lineHeight = 15.sp,
            )
            Text(
                "st 14.1.2024 20:00",
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 12.sp,
                lineHeight = 10.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 11.sp,
                lineHeight = 13.sp
            )
        }
    }
}