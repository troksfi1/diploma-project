package presentation.screens.events

sealed interface EventsEvent {
    object OnFilterTodayIsSelected : EventsEvent

    object OnFilterTomorrowIsSelected : EventsEvent

    object OnFilterDateIsSelected : EventsEvent

    object OnDatePickerDismissRequest : EventsEvent

    object OnDateSelected : EventsEvent
}