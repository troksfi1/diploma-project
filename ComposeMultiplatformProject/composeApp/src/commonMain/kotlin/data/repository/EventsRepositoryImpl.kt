package data.repository

import data.local.dao.EventsDao
import domain.model.Event
import domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val dao: EventsDao
) : EventsRepository {

    override fun getAllEvents(): List<Event> {
        return dao.getAllEvents()
    }
}