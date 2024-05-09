package cz.cvut.fit.nidip.troksfil

import cz.cvut.fit.nidip.troksfil.data.local.DatabaseDriverFactory
import cz.cvut.fit.nidip.troksfil.di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinHelper : KoinComponent

private val dbDriverFactoryModule = module {
    single { DatabaseDriverFactory() }
}

fun initKoin() {
    startKoin {
        modules(appModule() + dbDriverFactoryModule)
    }
}