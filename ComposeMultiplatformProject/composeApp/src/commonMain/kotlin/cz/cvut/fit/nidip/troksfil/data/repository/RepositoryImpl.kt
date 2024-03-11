package cz.cvut.fit.nidip.troksfil.data.repository

import cz.cvut.fit.nidip.troksfil.common.NullableInputListMapper
import cz.cvut.fit.nidip.troksfil.data.remote.rss.RssFeed
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.EventItem
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.NewsItem
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import cz.cvut.fit.nidip.troksfil.domain.repository.EventsRepository
import cz.cvut.fit.nidip.troksfil.domain.repository.NewsRepository

class RepositoryImpl(
    private val remoteDataSource: RssFeed?, // ala dao
    private val eventsDataMapper: NullableInputListMapper<EventItem, Event>,
    private val newsDataMapper: NullableInputListMapper<NewsItem, News>,
    //private val databaseDaoImpl: DatabaseDaoImpl
    //private val eventLocalDataSource: EventsDao,        //todo refactor?
    //private val newsLocalDataSource: NewsDao,
) : EventsRepository, NewsRepository {      // todo divide to news and events rep?

    override suspend fun getAllEvents(): List<Event> {
        return eventsDataMapper.map(remoteDataSource?.getEventsXml()?.rss?.channel)

        //current impl
        //return eventLocalDataSource.getAllEvents()

        /*val remoteResult = remoteDataSource?.getAllEvents()
        when (remoteResult) {
            is Result.Success -> {
                // When success replace the local database and return the result
                // You could also return the local data for a single source of truth pattern
                localDataSource.updateUserInfo(remoteResult.data)
                return Result.Success(remoteResult.data)
            }
            is Result.Error -> {
                // If error fallback to local database
                val localResult = localDataSource.getUserInfo()
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
        }*/
    }

    override fun removeAllNews() {
        //current impl
        //newsLocalDataSource.removeAllNews()
    }

    override suspend fun getAllNews(): List<News> {
        return newsDataMapper.map(remoteDataSource?.getNewsXml()?.rss?.channel)     // todo is it safe?
        //return databaseDaoImpl.getAllNews()
    }
}