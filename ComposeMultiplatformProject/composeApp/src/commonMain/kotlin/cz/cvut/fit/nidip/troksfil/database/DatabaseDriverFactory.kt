package cz.cvut.fit.nidip.troksfil.database

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory { //todo move to data layer ore move all folders to this package
    fun createDriver(): SqlDriver
}

/*
fun createDatabase(driverFactory: DatabaseDriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = AppDatabase(driver)

    // Do more work with the database (see below).
}*/
