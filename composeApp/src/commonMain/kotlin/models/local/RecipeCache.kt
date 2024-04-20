package models.local

import util.extractRecipeTitle

object RecipeCache {
    var recipe = Recipe.EMPTY

    @Suppress("unused")
    fun reset() {
        recipe = Recipe.EMPTY
    }

    suspend fun saveInDatabase(
        status: Status,
        courseType: String,
        sqlDataSourceImpl: SqlDataSourceImpl
    ) {
        status.saveRecipeInCache(courseType)
        sqlDataSourceImpl.insertRecentRecipe(
            imageUrl = recipe.imageUrl,
            title = recipe.content.extractRecipeTitle(),
            content = recipe.content,
            courseType = recipe.courseType,
            duration = recipe.duration,
            rating = recipe.rating,
        )
    }

    private fun Status.saveRecipeInCache(courseType: String) {
        when (this) {
            is Status.Success -> {
                recipe = recipe.copy(
                    title = this.data.extractRecipeTitle(),
                    content = this.data,
                    courseType = courseType
                )
            }
            is Status.Error -> {
                recipe = recipe.copy(content = this.message)
            }
            else -> Unit
        }
    }
}