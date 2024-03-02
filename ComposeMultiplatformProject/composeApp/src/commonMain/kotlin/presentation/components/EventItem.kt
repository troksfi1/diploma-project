package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.model.Event
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EventItem(event: Event, onItemClick: (Event) -> Unit) {

    Surface(
        modifier = Modifier
            .size(height = 200.dp, width = 175.dp)
            .padding(6.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .shadow(10.dp)
            .clickable { onItemClick(event) }
    ) {
        Column {
            Image(
                painter = painterResource(event.coverPhotoPath),
                contentDescription = "coverPhoto",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .aspectRatio(4f / 3f)
            )
            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    event.title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    /*event.category + */  event.dateTime,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    event.place,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}