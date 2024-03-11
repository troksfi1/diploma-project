package cz.cvut.fit.nidip.troksfil.domain.mappers

import co.touchlab.kermit.Logger
import cz.cvut.fit.nidip.troksfil.common.Mapper
import cz.cvut.fit.nidip.troksfil.common.NullableInputListMapper
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.EventItem
import cz.cvut.fit.nidip.troksfil.data.remote.rss.dto.NewsItem
import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


class EventItemXmlToModel : Mapper<EventItem, Event> {
    override fun map(input: EventItem): Event {
        return Event(
            id = input.guid.toInt(),
            categories = parseEventList(input.tag),
            place = "todo",
            title = input.title,
            startDateTime = parseDateTime(input.date.startDate, input.date.startTime),
            endDateTime = parseDateTime(input.date.endDate, input.date.endTime),
            description = input.text,
            imageUri = input.gallery.image.url,
            thumbnailUri = input.thumbnail
        )
    }
}

class NewsItemXmlToModel : Mapper<NewsItem, News> {
    override fun map(input: NewsItem): News {
        return News(
            id = input.guid.toInt(),
            title = input.title,
            pubDateTime = LocalDateTime.parse(input.pubDate.replace(" ", "T")),
            text = input.text,
            imageUri = if (input.gallery != null) {
                input.gallery.image.url
            } else {
                "https://pribram.eu/galerie_clanky/104990_med.jpg"
            },
            thumbnailUri = input.thumbnail ?: "https://pribram.eu/galerie_clanky/104990_med.jpg",
        )
    }
}

class EventItemsDtoToModel(
    private val itemToEvent: EventItemXmlToModel
) : NullableInputListMapper<EventItem, Event> {
    override fun map(input: List<EventItem>?): List<Event> {
        return input?.map { itemToEvent.map(it) }.orEmpty()
    }
}

class NewsItemsDtoToModel(
    private val itemToNews: NewsItemXmlToModel
) : NullableInputListMapper<NewsItem, News> {
    override fun map(input: List<NewsItem>?): List<News> {
        return input?.map { itemToNews.map(it) }.orEmpty()
    }
}

fun parseEventList(tags: String): List<EventCategory> {
    val eventCategoryList = mutableListOf<EventCategory>()
    for (tag in tags.split(",").map { it.trim() }) {
        try {
            EventCategory.entries.find { it.categoryName == tag }?.let { eventCategoryList.add(it) }
        } catch (_: IllegalArgumentException) {
            Logger.d("Wasn't able to parse $tag")
        }
    }
    return eventCategoryList
}

fun parseDateTime(date: String, time: String): LocalDateTime {
    return LocalDateTime(
        LocalDate.parse(date),
        LocalTime.parse(time),
    )
}