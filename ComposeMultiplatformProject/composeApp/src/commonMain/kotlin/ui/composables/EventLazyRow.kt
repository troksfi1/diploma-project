package ui.composables

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
import data.model.Event
import data.model.EventCategory
import kotlinx.coroutines.launch
import ui.screens.EventDetailScreen

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
        val eventList = listOf(
            Event(
                EventCategory.MUSIC.category,
                "Q-klub",
                "Jamaron",
                "st 14.1.2024 20:00",
                "Stálice Junioru, sázka na toho nejlepšího koně, záruka kvalitního večera. To je koncert příbramské kapely Jazz Faces - jejich repertoár je jak barevná paleta, kde se prolíná jazz, pop, soul a funk, a vytváří tak jedinečný hudební zážitek. Na čele této muzikantské pohody stojí charismatický frontman Jakub Ročňák.",
                "drawable/img_Jamaron.jpg"
            ),
            Event(
                EventCategory.THEATRE.category,
                "Divadlo Příbram - Velká scéna",
                "Jistě, pane ministře",
                "st 14.2.2024 19:00",
                "Dlouholetý opoziční politik Hacker získává po volbách jedno z ministerstev a s nejlepší vůlí se snaží vymést odsud přebujelou administrativu. Stálý tajemník ministerstva, Sir Appleby, absolutně neúnavný profesionál, má však také svůj trvalý záměr: koncentrovat moc v rukou státní správy a vyšachovat ministra ze hry. Vychází z předpokladu, že ani šéf, ani veřejnost by nikdy neměli vědět víc, než je třeba. Jak může nevyhlášený souboj těch dvou dopadnout?Divadelní verze kultovního britského televizního seriálu. Verze o desítky let mladší, a proto ostřejší, sofistikovanější, současná. Ač v britském prostředí, situace, postavy i vztahy mezi politiky jsou natolik typické, že českému divákovi snadno připomenou domácí realitu…",
                "drawable/img_theatreEvent.png"
            ),
            Event(
                EventCategory.CINEMA.category,
                "Divadlo Příbram - Kino",
                "Bob Marley: One Love",
                "út 13.1.2024 21:00",
                "Na konci srpna byla zahájena přestavba mostu v ulici Pod Kovárnami a lávky ve Špitálské ulici.",
                "drawable/img_cinemaEvent.png"
            ),
        )
        items(eventList) { event ->
            EventItem(event = event, onItemClick = { selectedEvent ->
                navigator.push(EventDetailScreen(event))
            })
        }
    }
}