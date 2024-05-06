package cz.cvut.fit.nidip.troksfil.presentation.screens.events


import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.FilterOption
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

data class EventsState(
    val events: StateFlow<List<Event>> = MutableStateFlow(emptyList()),
    val selectedFilterOption: FilterOption = FilterOption.TODAY,
    val timeFilteredEvents: List<Event> = emptyList(),
    val filteredEventsCategories: List<EventCategory> = emptyList(),
    val selectedDateStart: LocalDateTime = LocalDateTime(
        Clock.System.todayIn(TimeZone.currentSystemDefault()),
        LocalTime(0, 0)
    ),
    val selectedDateEnd: LocalDateTime =
        LocalDateTime(
        Clock.System.todayIn(TimeZone.currentSystemDefault()),
        LocalTime(23, 59)
    ),
    val isDatePickerOpen: Boolean = false,
    val isFetchingEvents: Boolean = false,
)
