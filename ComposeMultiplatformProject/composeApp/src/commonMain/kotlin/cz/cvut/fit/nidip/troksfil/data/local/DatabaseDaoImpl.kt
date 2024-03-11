package cz.cvut.fit.nidip.troksfil.data.local

import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import kotlinx.datetime.LocalDateTime

class DatabaseDaoImpl(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbNewsQuery = database.newsQueries
    private val dbEventQuery = database.eventsQueries


    suspend fun getAllEvents(): List<Event> {
        return dbEventQuery.selectAllEvents(::mapEventsSelecting).executeAsList()
    }

    /*override suspend fun getNewsById(id: Int): News? {
        return dbQuery.selectNewsById(id).
    }*/

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

    /*fun removeAllNews() {
        dbNewsQuery.transaction {
            dbNewsQuery.removeAllNews()
        }
    }*/

    suspend fun getAllNews(): List<News> {
        return dbNewsQuery.selectAllNews(::mapNewsSelecting).executeAsList()
    }

    /*override suspend fun getNewsById(id: Int): News? {
        return dbQuery.selectNewsById(id).
    }*/

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


