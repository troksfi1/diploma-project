package cz.cvut.fit.nidip.troksfil.data.local.dao

import cz.cvut.fit.nidip.troksfil.data.local.AppDatabase
import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDriverFactory
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.repository.EventsRepository
import kotlinx.datetime.LocalDateTime

class EventsDao(databaseDriverFactory: DatabaseDriverFactory) :
    EventsRepository {  // todo remove implement repository?
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.eventsQueries

    override suspend fun getAllEvents(): List<Event> {
        return dbQuery.selectAllEvents(::mapEventsSelecting).executeAsList()
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


