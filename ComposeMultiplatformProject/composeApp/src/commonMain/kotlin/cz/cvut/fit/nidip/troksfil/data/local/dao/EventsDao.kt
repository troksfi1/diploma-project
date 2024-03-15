package cz.cvut.fit.nidip.troksfil.data.local.dao

import cz.cvut.fit.nidip.troksfil.domain.model.Event

interface EventsDao {
    suspend fun insertAllEvents(events: List<Event>)
    suspend fun insertEventObject(event: Event)
    suspend fun getAllEvents(): List<Event>    //Flow<> ??? or suspend
    suspend fun deleteAllEvents()
}