package models.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import jr.brian.shared.database.AppDatabase

actual class DatabaseDriver(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = AppDatabase.Schema,
        context = context,
        name = DB_NAME
    )
}