package data.model

data class News(
    val id: Int,
    val dateTime: String,
    val title: String,
    val text: String,
    val author: String,
    val coverPhotoPath: String, //Blob
    //val photosPaths:  String// List<Blob>
)
