package cz.cvut.fit.nidip.troksfil.domain.repository

import cz.cvut.fit.nidip.troksfil.domain.model.Event
import cz.cvut.fit.nidip.troksfil.domain.model.News

interface Repository {

    fun removeAllNews()
    suspend fun getAllNews(): List<News>    //Flow<> ??? or suspend

    suspend fun getAllEvents(): List<Event>    //Flow<> ??? or suspend
}