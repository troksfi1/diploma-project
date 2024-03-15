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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val remoteDataSource: RssFeed?, // ala dao
    private val eventsDataMapper: NullableInputListMapper<EventItem, Event>,
    private val newsDataMapper: NullableInputListMapper<NewsItem, News>,
    private val localDataSource: DatabaseDaoImpl
    //private val eventLocalDataSource: EventsDao,        //todo refactor?
    //private val newsLocalDataSource: NewsDao,           // todo divide to news and events rep?
) : EventsRepository, NewsRepository {


    //val newsRequestState = remoteDataSource?.getEventsXml()

    private val _events: MutableStateFlow<List<Event>> =
        MutableStateFlow(emptyList()) // private mutable state flow

    var events = _events.asStateFlow() // publicly exposed as read-only state flow

    private val _news: MutableStateFlow<List<News>> =
        MutableStateFlow(emptyList()) // private mutable state flow

    var news = _news.asStateFlow() // publicly exposed as read-only state flow


    /*when (newsRequestState) {
        is Result.Success -> {
            // When success replace the local database and return the result
            // You could also return the local data for a single source of truth pattern
            localDataSource.updateUserInfo(newsRequestState.data)
            return Result.Success(newsRequestState.data)
        }
        is Result.Error -> {
            // If error fallback to local database
            val localResult = localDataSource.getAllEvents()
            when (localResult) {
                is Result.Success -> {
                    return Result.Success(localResult.data)
                }

                is Result.Error -> {
                    return Result.Error(localResult.exception)
                }

                is Result.Loading -> {
                    return Result.Loading
                }
            }
        }
        Unit -> {

        }
    }*/
    override suspend fun insertAllEvents(events: List<Event>) {
        localDataSource.insertAllEvents(events)
    }

    override suspend fun insertEvent(event: Event) {
        localDataSource.insertEventObject(event)
    }

    override suspend fun getAllEvents(): StateFlow<List<Event>> {      //todo can be replace by
        GlobalScope.launch {
            _events.update {
                async { eventsDataMapper.map(remoteDataSource?.getEventsXml()?.rss?.channel) }.await()
            }
        }

        //localDataSource.insertAllEvents(_events.value)
        Logger.d("get events from local called")
        localDataSource.deleteAllEvents()
        localDataSource.insertAllEvents(FakeRepositoryImpl().getAllEvents().value)

        _events.update { localDataSource.getAllEvents() }

        Logger.d("Method get events return")
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

        GlobalScope.launch {
            _news.update {
                Logger.d("get events from remote called")
                async { newsDataMapper.map(remoteDataSource?.getNewsXml()?.rss?.channel) }.await()
            }
        }

        //localDataSource.insertAllNews(_news.value)
        localDataSource.removeAllNews()
        localDataSource.insertAllNews(FakeRepositoryImpl().getAllNews().value)
        _news.update { localDataSource.getAllNews() }

        return news
    }

    override suspend fun deleteAllNews() {
        localDataSource.removeAllNews()
    }
}