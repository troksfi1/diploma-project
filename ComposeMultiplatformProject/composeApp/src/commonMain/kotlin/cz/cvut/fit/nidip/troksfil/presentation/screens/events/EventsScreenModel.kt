package cz.cvut.fit.nidip.troksfil.presentation.screens.events

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import cz.cvut.fit.nidip.troksfil.data.repository.RepositoryImpl
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.FilterOption
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

class EventsScreenModel(    // todo replace just by eventsRepository
    private val repository: RepositoryImpl
) : ScreenModel {

    private val _state = MutableStateFlow(EventsState())

    val state = _state.asStateFlow()

    fun onEvent(event: EventsEvent) {
        when (event) {
            EventsEvent.OnFilterTodayIsSelected -> {
                screenModelScope.launch {
                    val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
                    val timeFilteredEvents =
                        _state.value.events.value.filter { today in (it.startDateTime.date..it.endDateTime.date) }

                    _state.update {
                        it.copy(
                            selectedFilterOption = FilterOption.TODAY,
                            selectedDateStart = LocalDateTime(today, LocalTime(0, 0)),
                            selectedDateEnd = LocalDateTime(today, LocalTime(23, 59)),
                            timeFilteredEvents = timeFilteredEvents,
                            filteredEventsCategories = getCategoriesOfFilteredEvents(
                                timeFilteredEvents
                            )
                        )
                    }
                }
            }

            EventsEvent.OnFilterTomorrowIsSelected -> {
                screenModelScope.launch {
                    val tomorrow: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
                        .plus(1, DateTimeUnit.DAY)

                    val filteredEvents =
                        _state.value.events.value.filter { tomorrow in (it.startDateTime.date..it.endDateTime.date) }

                    _state.update {
                        it.copy(
                            timeFilteredEvents = filteredEvents,
                            selectedFilterOption = FilterOption.TOMORROW,       //todo it nebo _state
                            selectedDateStart = LocalDateTime(tomorrow, LocalTime(0, 0)),
                            selectedDateEnd = LocalDateTime(tomorrow, LocalTime(23, 59)),
                            filteredEventsCategories = getCategoriesOfFilteredEvents(filteredEvents)
                        )
                    }
                }
            }

            is EventsEvent.OnDateIsPicked -> {
                //parse input millis to date todo (isn't local!!!)
                val startDate =
                    LocalDate.fromEpochDays((event.startDate / 86400000).toInt()) //TODO OSETRIT VSTUPNI HONITY KDYZ NEJSOU ZADANY (pomoci compareTo()
                val endDate = LocalDate.fromEpochDays((event.endDate / 86400000).toInt())

                val startDateTime = LocalDateTime(startDate, LocalTime(0, 0))
                val endDateTime = LocalDateTime(endDate, LocalTime(23, 59))

                val myRange = startDateTime..endDateTime

                val filteredEvents =
                    _state.value.events.value.filter { event -> event.startDateTime in myRange } //todo filter based on time range

                _state.update {
                    it.copy(
                        timeFilteredEvents = filteredEvents,
                        isDatePickerOpen = false,
                        selectedDateStart = startDateTime,
                        selectedDateEnd = endDateTime,
                        selectedFilterOption = FilterOption.SELECTED_DATE,
                        filteredEventsCategories = getCategoriesOfFilteredEvents(filteredEvents)
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
                GlobalScope.launch(Dispatchers.IO) {
                    Logger.d("OnInit started")
                    val events = repository.getAllEvents()      // todo event filter by time
                    events.collect {
                        _state.update { state ->
                            state.copy(
                                events = events,
                            )
                        }
                        onEvent(EventsEvent.OnEventsStateChanged)
                        Logger.d("OnInit Events list changed")
                    }

                }
            }

            EventsEvent.OnEventsStateChanged -> {
                var timeFilteredEvents = emptyList<Event>()

                when (_state.value.selectedFilterOption) {
                    FilterOption.TODAY -> {
                        val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
                        timeFilteredEvents =
                            _state.value.events.value.filter { today in (it.startDateTime.date..it.endDateTime.date) }

                    }

                    FilterOption.TOMORROW -> {
                        val tomorrow: LocalDate =
                            Clock.System.todayIn(TimeZone.currentSystemDefault())
                                .plus(1, DateTimeUnit.DAY)
                        timeFilteredEvents =
                            _state.value.events.value.filter { tomorrow in (it.startDateTime.date..it.endDateTime.date) }
                    }

                    FilterOption.SELECTED_DATE -> {
                        //todo implement
                    }
                }

                _state.update { state ->
                    state.copy(
                        timeFilteredEvents = timeFilteredEvents,
                        filteredEventsCategories = getCategoriesOfFilteredEvents(timeFilteredEvents)
                    )
                }

            }
        }
    }

    init {
        onEvent(EventsEvent.OnInit) //todo move
    }

    private fun getCategoriesOfFilteredEvents(events: List<Event>): List<EventCategory> {
        val allCategories = mutableListOf<EventCategory>()
        events.forEach { allCategories.addAll(it.categories) }
        return allCategories.distinctBy { eventCategory -> eventCategory }
    }
}