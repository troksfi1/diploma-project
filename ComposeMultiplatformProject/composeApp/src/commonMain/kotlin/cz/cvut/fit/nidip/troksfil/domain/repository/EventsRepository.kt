package cz.cvut.fit.nidip.troksfil.domain.repository

import cz.cvut.fit.nidip.troksfil.domain.model.Event

//interface for all data sources (database, cache, api)
interface EventsRepository {

    suspend fun getAllEvents(): List<Event>    //Flow<> ??? or suspend

    //suspend fun getEventById(id: Int): Event?

}