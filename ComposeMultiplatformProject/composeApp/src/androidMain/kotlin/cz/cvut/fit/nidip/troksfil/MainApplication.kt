package cz.cvut.fit.nidip.troksfil

import android.app.Application
import cz.cvut.fit.nidip.troksfil.di.appModule
import org.koin.core.context.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule())
        }
    }
}