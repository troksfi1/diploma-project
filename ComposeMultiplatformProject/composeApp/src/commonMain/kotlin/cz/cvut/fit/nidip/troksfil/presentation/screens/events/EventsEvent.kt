package cz.cvut.fit.nidip.troksfil.presentation.screens.events

sealed interface EventsEvent {
    data object OnFilterTodayIsSelected : EventsEvent
    data object OnFilterTomorrowIsSelected : EventsEvent
    data object OnFilterDateIsSelected : EventsEvent
    data object OnDatePickerDismissRequest : EventsEvent
    data class OnDateIsPicked(val startDate: Long, val endDate: Long) : EventsEvent
    data object OnInit : EventsEvent
    data object OnEventsStateChanged : EventsEvent
}