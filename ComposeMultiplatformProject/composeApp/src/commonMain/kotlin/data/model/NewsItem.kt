package data.model

import java.sql.Blob

data class NewsItem(
    val dateTime: String,
    val title: String,
    val text: String,
    val author: String,
    val photos: List<Blob>
)
