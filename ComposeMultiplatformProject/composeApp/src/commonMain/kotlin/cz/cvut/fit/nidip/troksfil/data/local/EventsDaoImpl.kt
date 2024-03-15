package cz.cvut.fit.nidip.troksfil.data.local

/*class EventsDaoImpl(databaseDriverFactory: DatabaseDriverFactory) :
    EventsRepository {  // todo remove implement repository?
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.eventsQueries
    override suspend fun insertAllEvents(events: List<Event>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEvents(): List<Event> {
        return dbQuery.selectAllEvents(::mapEventsSelecting).executeAsList()
    }

    *//*override suspend fun getNewsById(id: Int): News? {
        return dbQuery.selectNewsById(id).
    }*//*

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
}*/


