package cz.cvut.fit.nidip.troksfil.data.local.dao

import cz.cvut.fit.nidip.troksfil.data.local.AppDatabase
import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDriverFactory
import cz.cvut.fit.nidip.troksfil.domain.model.News
import cz.cvut.fit.nidip.troksfil.domain.repository.NewsRepository
import kotlinx.datetime.LocalDateTime

class NewsDao(databaseDriverFactory: DatabaseDriverFactory) : NewsRepository {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.newsQueries

    override fun removeAllNews() {
        /*dbQuery.transaction {
            dbQuery.removeAllNews()
        }*/
    }

    override suspend fun getAllNews(): List<News> {
        return dbQuery.selectAllNews(::mapNewsSelecting).executeAsList()
    }

    /*override suspend fun getNewsById(id: Int): News? {
        return dbQuery.selectNewsById(id).
    }*/

    private fun mapNewsSelecting(
        id: Long,
        dateTime: String,
        title: String,
        text: String,
        thumbnailUri: String,
        imageUri: String
    ): News {
        return News(
            id = id.toInt(),
            pubDateTime = LocalDateTime.parse(dateTime),    // todo kdyz budu ukladat rss po mapperu tak se nic nezmeni
            title = title,
            text = text,
            thumbnailUri = thumbnailUri,
            imageUri = imageUri
        )
    }
}


