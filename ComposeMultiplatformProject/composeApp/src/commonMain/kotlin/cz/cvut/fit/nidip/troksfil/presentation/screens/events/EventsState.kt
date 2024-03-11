package cz.cvut.fit.nidip.troksfil.presentation.screens.events


import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.FilterOption
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import kotlinx.datetime.LocalDateTime

data class EventsState(
    val filteredEvents: List<Event> = emptyList(),
    val events: List<Event> = emptyList(),
    val selectedDateStart: LocalDateTime = LocalDateTime(1999, 2, 27, 20, 0),   //todo
    val selectedDateEnd: LocalDateTime = LocalDateTime(1999, 2, 27, 20, 0),   //todo
    val selectedFilterOption: FilterOption = FilterOption.TODAY,
    val isDatePickerOpen: Boolean = false,
    val filteredEventsCategories: List<EventCategory> = emptyList()
    /*val dateRangePickerState: DateRangePickerState = DateRangePickerState(
        initialSelectedStartDateMillis = null,
        initialDisplayedMonthMillis = null,
        yearRange = 2023..2023,
        initialDisplayMode = DisplayMode.Picker,
    )*/
    //= rememberDatePickerState(initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds())
)
