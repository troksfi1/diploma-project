package domain.repository

import domain.model.News

//interface for all data sources (database, cache, api)
interface NewsRepository {

    fun removeAllNews()
    fun getAllNews(): List<News>    //Flow<> ??? or suspend

    //suspend fun getNewsById(id: Int): News?

}