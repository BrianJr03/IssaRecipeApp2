package models.local

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val imageUrl: String,
    val title: String,
    val courseType: String,
    val duration: String,
    val rating: String
) {
    override fun toString(): String {
        return "imgUrl: $imageUrl\n" +
                "title: $title\n" +
                "course: $courseType\n" +
                "duration: $duration\n" +
                "rating: $rating"
    }

    @Suppress("unused")
    companion object {
        val EMPTY =
            Recipe(
                imageUrl = "",
                title = "",
                courseType = "",
                duration = "",
                rating = ""
            )
    }
}

private const val BACON = "https://upload.wikimedia.org/wikipedia/commons/2/20/Bacon_%281%29.jpg"

private const val APPLE = "https://pbs.twimg.com/profile_images/17170136649544990" +
        "72/2dcJ0Unw_400x400.png"

private const val EGG = "https://duet-cdn.vox-cdn.com/thumbor/0x0:876x584/1200x800/filters:f" +
        "ocal(438x292:439x293):format(webp)/cdn.vox-cdn.com/uploads/chorus_asset/file/" +
        "13689000/instagram_egg.jpg"


val TEST_RECENT_RECIPES = listOf( // TODO - Replace with actual data
    Recipe(
        BACON,
        "Bacon",
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        EGG,
        "Egg",
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        APPLE,
        "Apple",
        "Technology :(",
        "Since 1976",
        "3.0"
    ),
    Recipe(
        EGG,
        "Egg",
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        APPLE,
        "Apple",
        "Technology :(",
        "Since 1976",
        "3.0"
    ),
    Recipe(
        BACON,
        "Bacon",
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        EGG,
        "Egg",
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        BACON,
        "Bacon",
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        APPLE,
        "Apple",
        "Technology :(",
        "Since 1976",
        "3.0"
    )
)