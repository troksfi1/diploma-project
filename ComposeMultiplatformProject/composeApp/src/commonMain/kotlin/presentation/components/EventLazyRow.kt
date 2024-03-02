package presentation.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.repository.FakeEventsDataSourceImpl
import domain.EventCategory
import kotlinx.coroutines.launch
import presentation.screens.core.EventDetailScreen

@Composable
fun EventLazyRow(category: EventCategory) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val navigator: Navigator = LocalNavigator.currentOrThrow

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
        val eventList = FakeEventsDataSourceImpl().getAllEvents()
            .filter { p -> p.category == category.category }   //todo replace by viewModel

        items(eventList) { event ->
            EventItem(event = event, onItemClick = { selectedEvent ->
                navigator.push(EventDetailScreen(event))
            })
        }
    }
}