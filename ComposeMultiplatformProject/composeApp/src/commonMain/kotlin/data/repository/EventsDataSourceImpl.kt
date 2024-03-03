package data.repository

import data.local.dao.EventsDao
import domain.model.Event
import domain.repository.EventsDataSource

class EventsDataSourceImpl(
    private val dao: EventsDao
) : EventsDataSource {

    override fun getAllEvents(): List<Event> {
        return dao.getAllEvents()
    }
}