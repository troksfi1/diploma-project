package data.model

data class Event(
    val category: String,
    val place: String,
    val title: String,
    val dateTime: String,
    val description: String,
    val coverPhotoPath: String
)