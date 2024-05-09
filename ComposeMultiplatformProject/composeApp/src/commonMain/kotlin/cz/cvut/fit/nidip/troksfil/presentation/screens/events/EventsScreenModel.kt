package cz.cvut.fit.nidip.troksfil.presentation.screens.events

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cz.cvut.fit.nidip.troksfil.data.repository.RepositoryImpl
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.FilterOption
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
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

class EventsScreenModel(
    private val repository: RepositoryImpl
) : ScreenModel {

    private val _state = MutableStateFlow(EventsState())
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

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
                            filteredEventCategories = getCategoriesOfFilteredEvents(
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
                            filteredEventCategories = getCategoriesOfFilteredEvents(filteredEvents)
                        )
                    }
                }
            }

            is EventsEvent.OnDateIsPicked -> {
                screenModelScope.launch {
                    val startDate =
                        LocalDate.fromEpochDays((event.startDateMillis / 86400000).toInt())
                    val endDate = LocalDate.fromEpochDays((event.endDateMillis / 86400000).toInt())

                    val startDateTime = LocalDateTime(startDate, LocalTime(0, 0))
                    val endDateTime = LocalDateTime(endDate, LocalTime(23, 59))

                    val selectedRange = startDateTime..endDateTime

                    val filteredEvents =
                        _state.value.events.value.filter { event -> event.startDateTime in selectedRange } //todo filter based on time range

                    _state.update {
                        it.copy(
                            timeFilteredEvents = filteredEvents,
                            isDatePickerOpen = false,
                            selectedDateStart = startDateTime,
                            selectedDateEnd = endDateTime,
                            selectedFilterOption = FilterOption.SELECTED_DATE,
                            filteredEventCategories = getCategoriesOfFilteredEvents(filteredEvents)
                        )
                    }
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
        }
    }

    init {
        screenModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val events = repository.getAllEvents()
            onEvent(EventsEvent.OnFilterTodayIsSelected)

            events.collect {
                _state.update { state ->
                    state.copy(
                        events = events,
                    )
                }
            }
        }
    }

    private fun getCategoriesOfFilteredEvents(events: List<Event>): List<EventCategory> {
        val allCategories = mutableListOf<EventCategory>()
        events.forEach { allCategories.addAll(it.categories) }
        return allCategories.distinctBy { eventCategory -> eventCategory }
    }
}