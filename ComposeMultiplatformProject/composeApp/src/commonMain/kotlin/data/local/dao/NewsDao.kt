package data.local.dao

import cz.cvut.fit.nidip.troksfil.database.AppDatabase
import cz.cvut.fit.nidip.troksfil.database.DatabaseDriverFactory
import domain.model.News
import domain.repository.NewsRepository

class NewsDao(databaseDriverFactory: DatabaseDriverFactory) : NewsRepository {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.newsQueries

    override fun removeAllNews() {
        dbQuery.transaction {
            dbQuery.removeAllNews()
        }
    }

    override fun getAllNews(): List<News> {
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
        author: String,
        coverPhotoPath: String
    ): News {
        return News(
            id = id.toInt(),
            dateTime = dateTime,
            title = title,
            text = text,
            author = author,
            coverPhotoPath = coverPhotoPath
        )
    }
}


