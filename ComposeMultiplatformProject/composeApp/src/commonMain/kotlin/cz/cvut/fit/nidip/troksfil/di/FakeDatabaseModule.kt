package cz.cvut.fit.nidip.troksfil.di

import cz.cvut.fit.nidip.troksfil.data.repository.FakeRepositoryImpl
import org.koin.dsl.module

val fakeDatabaseModule = module {

    single {
        FakeRepositoryImpl()
    }
}