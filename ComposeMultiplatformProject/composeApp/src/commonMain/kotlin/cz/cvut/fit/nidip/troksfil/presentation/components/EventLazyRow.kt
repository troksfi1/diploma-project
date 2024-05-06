package cz.cvut.fit.nidip.troksfil.presentation.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.presentation.screens.core.EventDetailScreen
import cz.cvut.fit.nidip.troksfil.presentation.screens.events.EventsState
import kotlinx.coroutines.launch

@Composable
fun EventLazyRow(category: EventCategory, state: EventsState) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val navigator: Navigator = LocalNavigator.currentOrThrow

    Column {
        Text(
            category.categoryName,
            modifier = Modifier.align(Alignment.Start).padding(start = 6.dp),
            style = MaterialTheme.typography.titleLarge
        )
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

            val predicate: (EventCategory) -> Boolean = { it == category }
            val eventList =
                state.timeFilteredEvents.filter { event -> event.categories.any(predicate) }   //todo replace by viewModel impl

            //EventsEvent.getEventsByCategory(category)

            items(eventList) { event ->
                EventItem(event = event, onItemClick = {
                    navigator.push(EventDetailScreen(event))
                })
            }
        }
    }
}