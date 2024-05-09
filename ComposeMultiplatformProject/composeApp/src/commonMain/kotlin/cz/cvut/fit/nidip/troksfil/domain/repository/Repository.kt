package cz.cvut.fit.nidip.troksfil.domain.repository

import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import kotlinx.coroutines.flow.StateFlow

//interface for all data sources (database, cache, api)
interface Repository {

    suspend fun insertAllEvents(events: List<Event>)
    suspend fun insertEvent(event: Event)
    suspend fun getAllEvents(): StateFlow<List<Event>>
    suspend fun getNewestEvents(): StateFlow<List<Event>>
    suspend fun deleteAllEvents()
    suspend fun insertAllNews(news: List<News>)
    suspend fun insertNews(news: News)
    suspend fun getAllNews(): StateFlow<List<News>>
    suspend fun deleteAllNews()
}