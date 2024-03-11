package cz.cvut.fit.nidip.troksfil.domain.model

import kotlinx.datetime.LocalDateTime

data class News(
    val id: Int,
    val title: String,
    val pubDateTime: LocalDateTime,
    val text: String,
    val thumbnailUri: String,
    val imageUri: String,
)
