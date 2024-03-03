package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//todo move to data layer (entity)
@Serializable
data class News(
    @SerialName("id")
    val id: Int,
    @SerialName("date_time")
    val dateTime: String,
    @SerialName("title")
    val title: String,
    @SerialName("text")
    val text: String,
    @SerialName("author")
    val author: String,
    @SerialName("cover_photo_path")
    val coverPhotoURI: String, //Blob
    //val photosPaths:  String// List<Blob>
)
