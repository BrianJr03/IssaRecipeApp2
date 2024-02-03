package model.local

import kotlinx.serialization.Serializable

@Serializable
data class RecentRecipe(
    val imageUrl: String,
    val title: String,
    val courseType: String,
    val duration: String
) {
    override fun toString(): String {
        return "imgUrl: $imageUrl\n" +
                "title: $title\n" +
                "course: $courseType\n" +
                "duration: $duration"
    }

    @Suppress("unused")
    companion object {
        val EMPTY = RecentRecipe("", "", "", "")
    }
}

val TEST_RECENT_RECIPES = listOf( // TODO - Replace with actual data
    RecentRecipe(
        "",
        "1",
        "1",
        "1"
    ),
    RecentRecipe(
        "",
        "2",
        "2",
        "2"
    ),
    RecentRecipe(
        "",
        "3",
        "3",
        "3"
    )
)