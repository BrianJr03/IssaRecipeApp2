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

private const val COOKIE2 = "https://www.realsimple.com/thmb/uwmEcWtairipZTGavdWVbkV_dqw" +
        "=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/chocolatechip" +
        "-cookies_300-d6a402fc30814fdf87af28be97b5fcdc.jpg"

private const val APPLE = "https://pbs.twimg.com/profile_images/17170136649544990" +
        "72/2dcJ0Unw_400x400.png"

private const val COOKIE = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Choc" +
        "-Chip-Cookie.jpg/640px-Choc-Chip-Cookie.jpg"

val TEST_RECENT_RECIPES = listOf( // TODO - Replace with actual data
    Recipe(
        COOKIE2,
        "COOKIE 2",
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        COOKIE,
        "Cookie",
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
        COOKIE,
        "Cookie",
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
        COOKIE2,
        "COOKIE 2",
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        COOKIE,
        "Cookie",
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        COOKIE2,
        "COOKIE 2",
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