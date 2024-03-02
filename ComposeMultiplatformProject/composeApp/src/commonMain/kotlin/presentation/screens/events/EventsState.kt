package presentation.screens.events


import domain.FilterOption
import domain.model.Event
import kotlinx.datetime.LocalDate

data class EventsState(
    val events: List<Event> = emptyList(),
    val selectedDate: LocalDate = LocalDate(1999, 2, 27),   //todo
    val selectedFilterOption: FilterOption = FilterOption.TODAY,
    val isDatePickerOpen: Boolean = false,
    //val datePickerState: DatePickerState = DatePickerState()
    //= rememberDatePickerState(initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds())
)
