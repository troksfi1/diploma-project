package cz.cvut.fit.nidip.troksfil.data.local

/*
class NewsDaoImpl(databaseDriverFactory: DatabaseDriverFactory) : NewsRepository {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.newsQueries
    override suspend fun insertAllNews(news: List<News>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertNews(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun removeAllNews() {
        */
/*dbQuery.transaction {
            dbQuery.removeAllNews()
        }*//*

    }

    override suspend fun getAllNews(): List<News> {
        return dbQuery.selectAllNews(::mapNewsSelecting).executeAsList()
    }

    */
/*override suspend fun getNewsById(id: Int): News? {
        return dbQuery.selectNewsById(id).
    }*//*


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
*/


