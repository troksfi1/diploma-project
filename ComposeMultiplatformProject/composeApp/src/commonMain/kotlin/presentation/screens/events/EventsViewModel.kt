package presentation.screens.events

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.Event
import domain.repository.EventsRepository

class EventsViewModel(
    private val repository: EventsRepository
) : ViewModel() {

    fun getAllEvents(): List<Event> {
        return repository.getAllEvents()
    }

}
