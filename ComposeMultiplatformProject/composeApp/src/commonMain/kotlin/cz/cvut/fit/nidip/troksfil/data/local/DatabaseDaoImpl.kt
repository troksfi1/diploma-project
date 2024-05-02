package cz.cvut.fit.nidip.troksfil.data.local

import cz.cvut.fit.nidip.troksfil.data.local.dao.EventsDao
import cz.cvut.fit.nidip.troksfil.data.local.dao.NewsDao
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import czcvutfitnidiptroksfildatalocal.Events
import kotlinx.datetime.LocalDateTime

class DatabaseDaoImpl(databaseDriverFactory: DatabaseDriverFactory) : NewsDao, EventsDao {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbNewsQuery = database.newsQueries
    private val dbEventQuery = database.eventsQueries

    override suspend fun insertAllNews(news: List<News>) {
        news.forEach {
            insertNewsObject(it)
        }
    }

    override suspend fun insertNewsObject(news: News) {
        dbNewsQuery.insertNewsObject(
            czcvutfitnidiptroksfildatalocal.News(
                news.id.toLong(),
                news.pubDateTime.toString(),
                news.title,
                news.text,
                news.thumbnailUri,
                news.imageUri
            )
        )
    }

    private fun mapNewsSelecting(
        id: Long,
        dateTime: String,
        title: String,
        text: String,
        thumbnailUri: String,
        imageUri: String
    ): News {
        return News(
            id = id.toInt(),
            pubDateTime = LocalDateTime.parse(dateTime),    // todo kdyz budu ukladat rss po mapperu tak se nic nezmeni
            title = title,
            text = text,
            thumbnailUri = thumbnailUri,
            imageUri = imageUri
        )
    }

    override suspend fun getAllNews(): List<News> {
        return dbNewsQuery.selectAllNews(::mapNewsSelecting).executeAsList()
    }

    override suspend fun removeAllNews() {
        dbNewsQuery.transaction {
            dbNewsQuery.removeAllNews()
        }
    }

    override suspend fun insertAllEvents(events: List<Event>) {
        events.forEach {
            insertEventObject(it)
        }
    }

    override suspend fun insertEventObject(event: Event) {
        dbEventQuery.insertEventObject(
            Events(
                event.id.toLong(),
                event.categories.joinToString(separator = ",") /*{ it.categoryName }*/,
                event.place,
                event.title,
                event.startDateTime.toString(),
                event.endDateTime.toString(),
                event.description,
                event.imageUri,
                event.thumbnailUri
            )
        )
    }

    private fun mapEventsSelecting(     //mode to repository ala mapper
        id: Long,
        tags: String,
        place: String,
        title: String,
        startDateTime: String,
        endDateTime: String,
        description: String,
        imageUri: String,
        thumbnailUri: String
    ): Event {
        return Event(
            id = id.toInt(),
            categories = parseEventList(tags),
            place = place,
            title = title,
            startDateTime = LocalDateTime.parse(startDateTime),
            endDateTime = LocalDateTime.parse(endDateTime),  //add second parameter formatter?
            description = description,
            imageUri = imageUri,
            thumbnailUri = thumbnailUri
        )
    }

    override suspend fun getAllEvents(): List<Event> {
        return dbEventQuery.selectAllEvents(::mapEventsSelecting).executeAsList()
    }

    override suspend fun getNewestEvents(): List<Event> {
        return dbEventQuery.selectNewestEvents(::mapEventsSelecting).executeAsList()
    }

    override suspend fun deleteAllEvents() {
        dbEventQuery.removeAllEvents()
    }
}

fun parseEventList(tags: String): List<EventCategory> {
    val eventCategoryList = mutableListOf<EventCategory>()
    for (tag in tags.split(",").map { it.trim() }) {
        try {
            eventCategoryList.add(EventCategory.valueOf(tag))
        } catch (_: IllegalArgumentException) {
        }
    }
    return eventCategoryList
}