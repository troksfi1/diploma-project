package cz.cvut.fit.nidip.troksfil.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:AppDatabase.db")
        //val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        AppDatabase.Schema.synchronous().create(driver)
        return driver
    }
}