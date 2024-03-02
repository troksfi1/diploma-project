package presentation.screens.events

import cafe.adriel.voyager.core.model.ScreenModel
import domain.EventCategory
import domain.FilterOption
import domain.model.Event
import domain.repository.EventsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.xml.xml
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

class EventsScreenModel(
    private val eventsDataSource: EventsDataSource
) : ScreenModel {

    private val _state = MutableStateFlow(EventsState())
    val state = _state.asStateFlow()

    fun onEvent(event: EventsEvent) {
        when (event) {
            EventsEvent.OnFilterTodayIsSelected -> {
                val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
                _state.update { state ->
                    state.copy(
                        selectedFilterOption = FilterOption.TODAY,
                        selectedDate = today
                    )
                }
            }

            EventsEvent.OnFilterTomorrowIsSelected -> {
                val tomorrow: LocalDate =
                    Clock.System.todayIn(TimeZone.currentSystemDefault()).plus(1, DateTimeUnit.DAY)
                _state.update { state ->
                    state.copy(
                        selectedFilterOption = FilterOption.TOMORROW,
                        selectedDate = tomorrow
                    )
                }

            }

            EventsEvent.OnFilterDateIsSelected -> {
                _state.update { state ->
                    state.copy(
                        isDatePickerOpen = true
                    )
                }
            }

            EventsEvent.OnDatePickerDismissRequest -> {
                val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
                _state.update { state ->
                    state.copy(
                        isDatePickerOpen = false,
                        selectedDate = today,    //todo refactor?
                        selectedFilterOption = FilterOption.TODAY,

                        )
                }
            }

            EventsEvent.OnDateSelected -> {
                _state.update { state ->
                    state.copy(
                        isDatePickerOpen = false,
                        selectedDate = state.selectedDate,
                        //todo
                        selectedFilterOption = FilterOption.SELECTED_DATE,

                        )
                }
            }

            else -> Unit

        }

    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            xml(
                contentType = ContentType.Application.Xml,
                format = XML { //ContentType.Application.Rss  // XML = DefaultXml
                    xmlDeclMode = XmlDeclMode.Auto
                })
        }
    }

    init {
        suspend { getEvents() }
    }


    override fun onDispose() {
        super.onDispose()
        client.close()
    }

    private suspend fun getEvents(): List<Event> {
        return client.get("https://kalendar.pribram.eu/xmldata/akce/").body()
    }

    fun getAllEvents(): List<Event> {
        return eventsDataSource.getAllEvents()
    }

    fun getEventsByCategory(category: EventCategory): List<Event> {
        return eventsDataSource.getAllEvents().filter { p -> p.category == category.category }
    }

    fun getEventsByDate(date: DatePeriod): List<Event> {
        //todo parse string dateTime to date
        return eventsDataSource.getAllEvents().filter { event -> event.dateTime == date.toString() }
    }
}