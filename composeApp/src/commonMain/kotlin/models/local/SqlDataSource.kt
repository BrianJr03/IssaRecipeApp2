package models.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import jr.brian.shared.database.AppDatabase
import jrbrianshareddatabase.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

interface SqlDataSource {
    val favoriteRecipes : Flow<List<Recipe>>
    suspend fun removeAllRecipes()
    suspend fun deleteWithId(id: String)
    suspend fun insert(
        imageUrl: String,
        title: String,
        content: String,
        courseType: String,
        duration: String,
        rating: String
    )
}

internal class SqlDataSourceImpl internal constructor(
    private val database: AppDatabase,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SqlDataSource {

    override val favoriteRecipes: Flow<List<Recipe>> =
        database.appDatabaseQueries.selectAllRecipes().asFlow().mapToList(ioDispatcher)

    override suspend fun removeAllRecipes() {
        database.appDatabaseQueries.removeAllFavorites()
    }

    override suspend fun deleteWithId(id: String) {

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