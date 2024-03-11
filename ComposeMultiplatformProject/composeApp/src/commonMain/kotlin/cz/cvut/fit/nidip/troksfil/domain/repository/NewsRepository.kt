package cz.cvut.fit.nidip.troksfil.domain.repository

import cz.cvut.fit.nidip.troksfil.domain.model.News

//interface for all data sources (database, cache, api)
interface NewsRepository {

    fun removeAllNews()
    suspend fun getAllNews(): List<News>    //Flow<> ??? or suspend

    //suspend fun getNewsById(id: Int): News?

}