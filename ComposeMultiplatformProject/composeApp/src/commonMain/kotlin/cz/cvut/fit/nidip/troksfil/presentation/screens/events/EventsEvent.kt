package cz.cvut.fit.nidip.troksfil.presentation.screens.events

sealed interface EventsEvent {
    object OnFilterTodayIsSelected : EventsEvent
    object OnFilterTomorrowIsSelected : EventsEvent
    object OnFilterDateIsSelected : EventsEvent
    object OnDatePickerDismissRequest : EventsEvent
    data class OnDateIsPicked(val startDate: Long, val endDate: Long) : EventsEvent
}