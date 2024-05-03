package cz.cvut.fit.nidip.troksfil.di

import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDaoImpl
import cz.cvut.fit.nidip.troksfil.data.remote.rss.RssFeed
import cz.cvut.fit.nidip.troksfil.data.repository.RepositoryImpl
import cz.cvut.fit.nidip.troksfil.domain.mappers.EventItemXmlToModel
import cz.cvut.fit.nidip.troksfil.domain.mappers.EventItemsDtoToModel
import cz.cvut.fit.nidip.troksfil.domain.mappers.NewsItemXmlToModel
import cz.cvut.fit.nidip.troksfil.domain.mappers.NewsItemsDtoToModel
import org.koin.dsl.module

val commonModule = module {

    single {
        RepositoryImpl(
            remoteDataSource = RssFeed(),
            eventsDataMapper = EventItemsDtoToModel(EventItemXmlToModel()),
            newsDataMapper = NewsItemsDtoToModel(NewsItemXmlToModel()),
            localDataSource = get()
        )
    }

    single<DatabaseDaoImpl> { DatabaseDaoImpl(get()) }
}