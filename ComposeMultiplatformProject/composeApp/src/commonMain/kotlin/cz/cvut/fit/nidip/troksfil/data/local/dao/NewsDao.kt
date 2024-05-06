package cz.cvut.fit.nidip.troksfil.data.local.dao

import cz.cvut.fit.nidip.troksfil.domain.model.News

interface NewsDao {
    suspend fun insertAllNews(news: List<News>)
    suspend fun insertNewsObject(news: News)
    suspend fun getAllNews(): List<News>
    suspend fun removeAllNews()
}