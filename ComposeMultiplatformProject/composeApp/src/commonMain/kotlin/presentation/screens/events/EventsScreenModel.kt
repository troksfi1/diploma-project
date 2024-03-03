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
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
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

                val filteredEventsCategories = getFilteredEventsCategories()
                val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

                val startDateTime = LocalDateTime(today, LocalTime(0, 0))
                val endDateTime = LocalDateTime(today, LocalTime(23, 59))

                val myRange = startDateTime..endDateTime

                val filteredEvents =
                    state.value.events.filter { event -> event.dateTime in myRange }

                _state.update {
                    it.copy(
                        filteredEvents = filteredEvents,
                        selectedFilterOption = FilterOption.TODAY,
                        selectedDateStart = startDateTime,
                        selectedDateEnd = endDateTime,
                        filteredEventsCategories = filteredEventsCategories
                    )
                }
            }

            EventsEvent.OnFilterTomorrowIsSelected -> {
                val filteredEventsCategories = getFilteredEventsCategories()
                val tomorrow: LocalDate =
                    Clock.System.todayIn(TimeZone.currentSystemDefault()).plus(1, DateTimeUnit.DAY)

                val startDateTime = LocalDateTime(tomorrow, LocalTime(0, 0))
                val endDateTime = LocalDateTime(tomorrow, LocalTime(23, 59))

                val myRange = startDateTime..endDateTime

                _state.update {
                    it.copy(
                        filteredEvents = it.events.filter { event -> event.dateTime in myRange },
                        selectedFilterOption = FilterOption.TOMORROW,       //todo it nebo _state
                        selectedDateStart = startDateTime,
                        selectedDateEnd = endDateTime,
                        filteredEventsCategories = filteredEventsCategories
                    )
                }

            }

            is EventsEvent.OnDateIsPicked -> {
                //parse input millis to date todo (isn't local!!!)
                val startDate =
                    LocalDate.fromEpochDays((event.startDate / 86400000).toInt()) //TODO OSETRIT VSTUPNI HONITY KDYZ NEJSOU ZADANY (pomoci compareTo()
                val endDate = LocalDate.fromEpochDays((event.endDate / 86400000).toInt())
                val filteredEventsCategories = getFilteredEventsCategories()

                val startDateTime = LocalDateTime(startDate, LocalTime(0, 0))
                val endDateTime = LocalDateTime(endDate, LocalTime(23, 59))

                val myRange = startDateTime..endDateTime

                _state.update {
                    it.copy(
                        filteredEvents = it.events.filter { event -> event.dateTime in myRange },
                        isDatePickerOpen = false,
                        selectedDateStart = startDateTime,
                        selectedDateEnd = endDateTime,
                        //todo
                        selectedFilterOption = FilterOption.SELECTED_DATE,
                        filteredEventsCategories = filteredEventsCategories
                    )
                }
            }

            EventsEvent.OnFilterDateIsSelected -> {
                _state.update {
                    it.copy(
                        isDatePickerOpen = true,
                    )
                }
            }

            EventsEvent.OnDatePickerDismissRequest -> {
                _state.update {
                    it.copy(
                        isDatePickerOpen = false,
                    )
                }
            }

            else -> Unit

        }

    }

    private fun getFilteredEventsCategories(): List<EventCategory> {
        val categoryList = _state.value.filteredEvents.map { it.category }
        return categoryList.distinctBy { it }
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
        //suspend { getEvents() }
        EventsEvent.OnFilterTodayIsSelected  //todo don't call if wasn't selected
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
        return eventsDataSource.getAllEvents().filter { p -> p.category == category }
    }

    /*fun getEventsByDate(date: DatePeriod): List<Event> {
        //todo parse string dateTime to date
        return eventsDataSource.getAllEvents().filter { event -> event.dateTime == date }
    }*/
}