package presentation.screens.events


import data.repository.FakeEventsDataSourceImpl
import domain.EventCategory
import domain.FilterOption
import domain.model.Event
import kotlinx.datetime.LocalDateTime

data class EventsState(
    val filteredEvents: List<Event> = emptyList(),
    val events: List<Event> = FakeEventsDataSourceImpl().getAllEvents(),//emptyList(),
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
