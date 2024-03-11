package cz.cvut.fit.nidip.troksfil.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import cz.cvut.fit.nidip.troksfil.domain.model.News
import org.jetbrains.compose.resources.ExperimentalResourceApi

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
        AsyncImage(
            model = news.thumbnailUri,
            contentDescription = "Sample",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .aspectRatio(4f / 3f)
        )
        Spacer(Modifier.width(6.dp))
        Column {
            Text(
                news.title,
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                news.pubDateTime.toString(),
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                news.text,
                modifier = Modifier
                    .align(Alignment.Start),
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}