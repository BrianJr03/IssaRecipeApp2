package models.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import jr.brian.shared.database.AppDatabase
import jrbrianshareddatabase.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

private interface SqlDataSource {
    val recipes : Flow<List<Recipe>>
    suspend fun removeAllRecipes()
    suspend fun deleteWithId(content: String)
    suspend fun insert(
        imageUrl: String,
        title: String,
        content: String,
        courseType: String,
        duration: String,
        rating: String
    )
}

class SqlDataSourceImpl internal constructor(
    private val database: AppDatabase,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SqlDataSource {

    override val recipes: Flow<List<Recipe>> =
        database.appDatabaseQueries.selectAllRecipes().asFlow().mapToList(ioDispatcher)

    override suspend fun removeAllRecipes() {
        database.appDatabaseQueries.removeAllRecentRecipes()
    }

    override suspend fun deleteWithId(content: String) {
        database.appDatabaseQueries.removeRecentRecipe(content)
    }

    override suspend fun insert(
        imageUrl: String,
        title: String,
        content: String,
        courseType: String,
        duration: String,
        rating: String
    ) {
        database.appDatabaseQueries.insertRecipe(
            imageUrl,
            title,
            content,
            courseType,
            duration,
            rating
        )
    }
}