package cz.cvut.fit.nidip.troksfil.presentation.screens.events

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.FilterOption
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.repository.EventsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.xml.xml
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val eventsRepository: EventsRepository
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
                    state.value.events.filter { it.startDateTime in myRange }

                screenModelScope.launch {

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
            }

            EventsEvent.OnFilterTomorrowIsSelected -> {
                screenModelScope.launch {
                    val filteredEventsCategories = getFilteredEventsCategories()
                    val tomorrow: LocalDate =
                        Clock.System.todayIn(TimeZone.currentSystemDefault())
                            .plus(1, DateTimeUnit.DAY)

                    val startDateTime = LocalDateTime(
                        tomorrow,
                        LocalTime(0, 0)
                    )        //todo replace by model attributes
                    val endDateTime = LocalDateTime(tomorrow, LocalTime(23, 59))

                    val myRange = startDateTime..endDateTime

                    _state.update {
                        it.copy(
                            filteredEvents = it.events.filter { event -> event.startDateTime in myRange },
                            selectedFilterOption = FilterOption.TOMORROW,       //todo it nebo _state
                            selectedDateStart = startDateTime,
                            selectedDateEnd = endDateTime,
                            filteredEventsCategories = filteredEventsCategories
                        )
                    }
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
                        filteredEvents = it.events.filter { event -> event.startDateTime in myRange },
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

            EventsEvent.OnInit -> {
                screenModelScope.launch {
                    _state.update { state ->
                        state.copy(
                            //news = repository.getAllNews(),
                            events = eventsRepository.getAllEvents()
                        )
                    }
                }
            }

            else -> Unit

        }

    }

    private fun getFilteredEventsCategories(): List<EventCategory> {
        val allCategories = mutableListOf<EventCategory>()
        _state.value.filteredEvents.forEach {
            allCategories.addAll(it.categories)
        }
        return allCategories.distinctBy { eventCategory -> eventCategory }
    }

    val client = HttpClient {
        install(ContentNegotiation) {
            xml(
                contentType = ContentType.Application.Xml,
                format = XML { //ContentType.Application.Rss  // XML = DefaultXml
                    xmlDeclMode = XmlDeclMode.Auto
                })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d { message }
                }
            }
            level = LogLevel.INFO
        }
    }

    init {
        EventsEvent.OnInit
        EventsEvent.OnFilterTodayIsSelected  //todo don't call if wasn't selected
    }


    suspend fun getEvents(): List<Event> {
        return eventsRepository.getAllEvents()
    }

    /*fun getEventsByDate(date: DatePeriod): List<Event> {
        //todo parse string dateTime to date
        return eventsDataSource.getAllEvents().filter { event -> event.dateTime == date }
    }*/
}