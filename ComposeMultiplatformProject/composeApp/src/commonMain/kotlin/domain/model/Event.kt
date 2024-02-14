package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//todo move to data layer (entity)
@Serializable
data class Event(
    @SerialName("category")
    val category: String,
    @SerialName("place")
    val place: String,
    @SerialName("title")
    val title: String,
    @SerialName("date_time")
    val dateTime: String,
    @SerialName("description")
    val description: String,
    @SerialName("cover_photo_path")
    val coverPhotoPath: String
) {
    // var launchYear = dateTime.toInstant().toLocalDateTime(TimeZone.UTC).year example to convert
}

/*{

    //option set val category: Int and then
    companion object {
        val eventCategory = listOf("Hudba", "Divadlo", "Kino")
    }
}*/