package ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import data.model.News
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NewsItem(news: News, onItemClick: (News) -> Unit) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(6.dp)
            .clickable { onItemClick(news) },
        horizontalArrangement = Arrangement.Start

    ) {
        Image(
            painter = painterResource(news.coverPhotoPath),
            contentDescription = "Sample",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .aspectRatio(4f / 3f)
                .size(10.dp)
        )
        Spacer(Modifier.width(6.dp))
        Column {
            Text(
                news.title,
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 15.sp,
                lineHeight = 15.sp,
            )
            Text(
                news.dateTime,
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 12.sp,
                lineHeight = 10.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(10.dp))
            Text(
                news.text,
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 11.sp,
                lineHeight = 13.sp
            )
        }
    }
}