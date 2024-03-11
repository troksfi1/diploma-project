package cz.cvut.fit.nidip.troksfil.data.local.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("date_time")
    val pubDateTime: String,
    @SerialName("text")
    val text: String,
    @SerialName("thumbnail_uri")
    val thumbnailUri: String,
    @SerialName("image_uri")
    val imageUri: String,
)