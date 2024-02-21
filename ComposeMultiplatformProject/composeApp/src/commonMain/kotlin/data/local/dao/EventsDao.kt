package data.local.dao

import cz.cvut.fit.nidip.troksfil.database.AppDatabase
import cz.cvut.fit.nidip.troksfil.database.DatabaseDriverFactory
import domain.model.Event
import domain.repository.EventsRepository

class EventsDao(databaseDriverFactory: DatabaseDriverFactory) : EventsRepository {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.eventsQueries

    override fun getAllEvents(): List<Event> {
        return dbQuery.selectAllEvents(::mapEventsSelecting).executeAsList()
    }

    /*override suspend fun getNewsById(id: Int): News? {
        return dbQuery.selectNewsById(id).
    }*/

    private fun mapEventsSelecting(
        id: Long,
        category: String,
        place: String,
        title: String,
        dateTime: String,
        description: String,
        coverPhotoPath: String
    ): Event {
        return Event(
            id = id.toInt(),
            category = category,
            place = place,
            title = title,
            dateTime = dateTime,
            description = description,
            coverPhotoPath = coverPhotoPath
        )
    }


}


