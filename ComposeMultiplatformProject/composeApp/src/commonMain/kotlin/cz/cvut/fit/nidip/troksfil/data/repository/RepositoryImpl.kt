package cz.cvut.fit.nidip.troksfil.data.repository

import co.touchlab.kermit.Logger
import cz.cvut.fit.nidip.troksfil.common.NullableInputListMapper
import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDaoImpl
import cz.cvut.fit.nidip.troksfil.data.remote.rss.RssFeed
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.EventItem
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.NewsItem
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import cz.cvut.fit.nidip.troksfil.domain.repository.EventsRepository
import cz.cvut.fit.nidip.troksfil.domain.repository.NewsRepository
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.measureTime

class RepositoryImpl(
    private val remoteDataSource: RssFeed?, // ala dao
    private val eventsDataMapper: NullableInputListMapper<EventItem, Event>,
    private val newsDataMapper: NullableInputListMapper<NewsItem, News>,
    private val localDataSource: DatabaseDaoImpl
    //private val eventLocalDataSource: EventsDao,        //todo refactor?
    //private val newsLocalDataSource: NewsDao,           // todo divide to news and events rep?
) : EventsRepository, NewsRepository {

    private val _events: MutableStateFlow<List<Event>> =
        MutableStateFlow(emptyList()) // private mutable state flow

    var events = _events.asStateFlow() // publicly exposed as read-only state flow

    private val _news: MutableStateFlow<List<News>> =
        MutableStateFlow(emptyList()) // private mutable state flow

    var news = _news.asStateFlow() // publicly exposed as read-only state flow

    //var newsUpToDate = .asStateFlow() // publicly exposed as read-only state flow

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
    override suspend fun insertAllEvents(events: List<Event>) {
        localDataSource.insertAllEvents(events)
    }

    override suspend fun insertEvent(event: Event) {
        localDataSource.insertEventObject(event)
    }

    override suspend fun getAllEvents(): StateFlow<List<Event>> {

        val timeToLoadAllEventsFromDB = measureTime {
            _events.update { localDataSource.getAllEvents() }
        }
        println("TAG Time to load all ${events.value.size} events from DB: ${timeToLoadAllEventsFromDB.inWholeMilliseconds} ")

        try {
            GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                _events.update {
                    eventsDataMapper.map(remoteDataSource?.getEventsXml()?.rss?.channel)
                }
                localDataSource.insertAllEvents(events.value)
            }
        } catch (e: ServerResponseException) {
            Logger.d("Error getting remote items: ${e.response.status.description}")
        }
        return events
    }

    override suspend fun getNewestEvents(): StateFlow<List<Event>> {

        _events.update { localDataSource.getNewestEvents() }

        try {
            GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                _events.update {
                    eventsDataMapper.map(remoteDataSource?.getEventsXml()?.rss?.channel)
                }
            }
        } catch (e: ServerResponseException) {
            Logger.d("Error getting remote items: ${e.response.status.description}")
        }

        return events
    }

    override suspend fun deleteAllEvents() {
        localDataSource.deleteAllEvents()
    }

    override suspend fun insertAllNews(news: List<News>) {
        localDataSource.insertAllNews(news)
    }

    override suspend fun insertNews(news: News) {
        localDataSource.insertNewsObject(news)
    }

    override suspend fun getAllNews(): StateFlow<List<News>> {
        val timeToLoadAllNewsFromDB = measureTime {
            _news.update { localDataSource.getAllNews() }
        }
        println("Time to load all news from DB: ${timeToLoadAllNewsFromDB.inWholeMilliseconds}")

        try {
            GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                val timeOfNetworkCall = measureTime {
                    _news.update {
                        newsDataMapper.map(remoteDataSource?.getNewsXml()?.rss?.channel)
                    }
                }
                println("News network request + DB took: ${timeOfNetworkCall.inWholeMilliseconds}")

                val insertToDB = measureTime {
                    localDataSource.insertAllNews(news.value)
                }
                println("load from DB after network call: ${insertToDB.inWholeMilliseconds}")
            }
        } catch (e: ServerResponseException) {
            Logger.d("Error getting remote items: ${e.response.status.description}")
        }
        return news
    }

    override suspend fun deleteAllNews() {
        localDataSource.removeAllNews()
    }
}