package models.local

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

private const val TEST_URL = "https://static.displate.com/857x1200/displate/" +
        "2023-04-03/742b3219d06cc23a4a0600f9ea17110b_4da3643de47c8a26b0d1a3de3d2daa54.jpg"

val TEST_RECENT_RECIPES = listOf( // TODO - Replace with actual data
    RecentRecipe(
        TEST_URL,
        "Test Recipe 1",
        "Lunch",
        "40 Min"
    ),
    RecentRecipe(
        TEST_URL,
        "Test Recipe 2",
        "Breakfast",
        "20 Min"
    ),
    RecentRecipe(
        TEST_URL,
        "Test Recipe 3",
        "Dinner",
        "35 Min"
    )
)