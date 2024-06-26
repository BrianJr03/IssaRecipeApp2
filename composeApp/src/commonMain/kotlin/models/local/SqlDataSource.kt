package models.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import jr.brian.shared.database.AppDatabase
import jrbrianshareddatabase.FavoriteRecipes
import jrbrianshareddatabase.Recipe
import models.local.Recipe as LocalRecipe
import jrbrianshareddatabase.Settings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

private interface SqlDataSource {
    val recipes: Flow<List<Recipe>>
    val favoriteRecipes: Flow<List<FavoriteRecipes>>
    val settings: Flow<Settings>
    suspend fun removeAllRecipes()
    suspend fun deleteWithId(content: String)
    suspend fun updateApiKey(value: String)
    suspend fun updateDietarySetting(value: String)
    suspend fun updateAllergySetting(value: String)
    suspend fun insertRecentRecipe(
        imageUrl: String,
        title: String,
        content: String,
        courseType: String,
        duration: String,
        rating: String
    )

    suspend fun insertFavoriteRecipe(
        imageUrl: String,
        title: String,
        content: String,
        courseType: String,
        duration: String,
        rating: String
    )

    suspend fun updateRecentRecipe(
        recipe: LocalRecipe,
        title: String
    )

    suspend fun initSettings()
}

class SqlDataSourceImpl internal constructor(
    private val database: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SqlDataSource {

    override val recipes: Flow<List<Recipe>> =
        database.appDatabaseQueries.selectAllRecipes().asFlow().mapToList(ioDispatcher)

    override val favoriteRecipes: Flow<List<FavoriteRecipes>> =
        database.appDatabaseQueries.selectAllFavoriteRecipes().asFlow().mapToList(ioDispatcher)

    override val settings: Flow<Settings>
        get() = database.appDatabaseQueries.getSettings().asFlow().mapToOne(ioDispatcher)

    override suspend fun removeAllRecipes() {
        database.appDatabaseQueries.removeAllRecentRecipes()
    }

    override suspend fun deleteWithId(content: String) {
        database.appDatabaseQueries.removeRecentRecipe(content)
    }

    override suspend fun insertRecentRecipe(
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

    override suspend fun insertFavoriteRecipe(
        imageUrl: String,
        title: String,
        content: String,
        courseType: String,
        duration: String,
        rating: String
    ) {
        database.appDatabaseQueries.insertFavoriteRecipe(
            imageUrl,
            title,
            content,
            courseType,
            duration,
            rating
        )
    }

    override suspend fun updateRecentRecipe(
        recipe: LocalRecipe,
        title: String
    ) {
        database.appDatabaseQueries.updateRecentRecipe(
            newImageUrl = recipe.imageUrl,
            newTitle = title,
            newContent = recipe.content,
            newCourseType = recipe.courseType,
            newDuration = recipe.duration,
            newRating = recipe.rating,
            oldContent = recipe.content
        )
    }

    override suspend fun initSettings() {
        database.appDatabaseQueries.initSettings(
            apiKey = "",
            dietarySettings = "",
            allergySettings = ""
        )
    }

    override suspend fun updateApiKey(value: String) {
        database.appDatabaseQueries.updateApiKey(value)
    }

    override suspend fun updateDietarySetting(value: String) {
        database.appDatabaseQueries.updatedietarySettings(value)
    }

    override suspend fun updateAllergySetting(value: String) {
        database.appDatabaseQueries.updateAllergySettings(value)
    }
}