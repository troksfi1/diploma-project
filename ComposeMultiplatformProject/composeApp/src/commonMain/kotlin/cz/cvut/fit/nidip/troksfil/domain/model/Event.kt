package cz.cvut.fit.nidip.troksfil.domain.model

import cz.cvut.fit.nidip.troksfil.domain.EventCategory
import kotlinx.datetime.LocalDateTime

data class Event(
    val id: Int,
    val categories: List<EventCategory>,
    val place: String,
    val title: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val description: String,
    val imageUri: String,
    val thumbnailUri: String
)

/*{
    //option set val category: Int and then
    companion object {
        val eventCategory = listOf("Hudba", "Divadlo", "Kino")
    }
}*/