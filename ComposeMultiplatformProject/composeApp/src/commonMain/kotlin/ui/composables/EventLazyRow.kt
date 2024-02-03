package ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EventLazyRow() {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(
        state = scrollState,
        modifier = Modifier
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    coroutineScope.launch {
                        scrollState.scrollBy(-delta)
                    }
                },
            )
    ) {
        items(100) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 175.dp)
                    .padding(6.dp)
                    .background(color = MaterialTheme.colorScheme.background)
                    .shadow(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {

                    Image(
                        painter = painterResource("drawable/img_Jamaron.jpg"),
                        contentDescription = "Sample",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .aspectRatio(4f / 3f)
                    )
                    Text(
                        "Jamaron",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Start),
                        fontSize = 15.sp
                    )
                    Text(
                        "Koncerty, st 14.2.2024 20:00 \nQ Klub",
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Start),
                        fontSize = 10.sp,
                        lineHeight = 10.sp
                    )
                }
            }
        }
    }
}