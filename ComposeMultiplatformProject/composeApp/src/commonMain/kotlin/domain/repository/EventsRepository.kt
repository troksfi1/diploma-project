package domain.repository

import domain.model.Event

//interface for all data sources (database, cache, api)
interface EventsRepository {

    fun getAllEvents(): List<Event>    //Flow<> ??? or suspend

    //suspend fun getEventById(id: Int): Event?

}