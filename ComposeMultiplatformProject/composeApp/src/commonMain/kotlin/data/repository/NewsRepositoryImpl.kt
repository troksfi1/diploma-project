package data.repository

import data.local.dao.NewsDao
import domain.model.News
import domain.repository.NewsRepository

//impl of dao and api
class NewsRepositoryImpl(
    private val dao: NewsDao
) : NewsRepository {

    override fun removeAllNews() {
        dao.removeAllNews()
    }

    override fun getAllNews(): List<News> {
        return dao.getAllNews()
    }

}