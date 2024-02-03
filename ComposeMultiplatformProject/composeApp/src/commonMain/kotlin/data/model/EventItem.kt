package data.model

import java.sql.Blob

data class EventItem(
    val category: String,
    val place: String,
    val title: String,
    val dateTime: String,
    val text: String,
    val photos: List<Blob>
)