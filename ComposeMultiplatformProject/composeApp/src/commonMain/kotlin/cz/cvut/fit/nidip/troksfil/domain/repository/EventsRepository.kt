package cz.cvut.fit.nidip.troksfil.domain.repository

import cz.cvut.fit.nidip.troksfil.domain.model.Event
import kotlinx.coroutines.flow.StateFlow

//interface for all data sources (database, cache, api)
interface EventsRepository {

    suspend fun insertAllEvents(events: List<Event>)
    suspend fun insertEvent(event: Event)
    suspend fun getAllEvents(): StateFlow<List<Event>>
    suspend fun getNewestEvents(): StateFlow<List<Event>>
    suspend fun deleteAllEvents()
}