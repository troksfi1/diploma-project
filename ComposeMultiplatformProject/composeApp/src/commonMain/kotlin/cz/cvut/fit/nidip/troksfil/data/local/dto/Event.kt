package cz.cvut.fit.nidip.troksfil.data.local.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("id")
    val id: Int,
    @SerialName("category")
    val categories: String,
    @SerialName("place")
    val place: String,
    @SerialName("title")
    val title: String,
    @SerialName("start_date_time")
    val startDateTime: String,
    @SerialName("end_date_time")
    val endDateTime: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_uri")
    val imageUri: String,
    @SerialName("thumbnail_uri")
    val thumbnailUri: String
)
